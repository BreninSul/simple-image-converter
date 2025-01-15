/*
 * MIT License
 * Copyright (c) 2024 BreninSul
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.breninsul.simpleimageconvertor.service.writer

import com.ashampoo.kim.Kim
import com.ashampoo.kim.format.tiff.constant.TiffTag
import com.ashampoo.kim.input.ByteReader
import com.ashampoo.kim.input.JvmInputStreamByteReader
import com.ashampoo.kim.model.MetadataUpdate
import com.ashampoo.kim.model.TiffOrientation
import com.ashampoo.kim.output.OutputStreamByteWriter
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.Ordered
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.getSetting
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.FlipSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.RotateSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TransformSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.writer.ConvertSettings
import io.github.breninsul.simpleimageconvertor.dto.supportsKimMetadataWrite
import org.apache.commons.io.output.QueueOutputStream
import java.io.OutputStream
import java.util.function.Supplier
import java.util.logging.Level
import java.util.logging.Logger


/** An interface for writing images in various formats. */
interface ImageWriter : Ordered {
    /**
     * Checks if the specified media type is supported by this image writer.
     *
     * @param mediaType the media type to check
     * @return `true` if the media type is supported, `false` otherwise
     */
    fun supports(mediaType: ImageFormat): Boolean {
        return supportedTypes().any { mediaType == it }
    }

    /**
     * Returns the set of supported image formats by this image writer.
     *
     * @return the set of supported image formats
     */
    fun supportedTypes(): Set<ImageFormat>

    /**
     * Retrieves the first supported image format for this image writer.
     *
     * @return the first supported image format if available
     * @throws IllegalStateException if no supported image formats are found
     */
    fun getImageFormat(): ImageFormat{
        return supportedTypes().firstOrNull()?:throw IllegalStateException("No supported types")
    }

    /**
     * Writes an image or animation to the specified output stream using the provided settings.
     * The method handles image orientation based on the original metadata and processes it appropriately
     * according to the orientation handling mode specified in the settings.
     *
     * @param image The image or animation to be written. It must be an instance of `ImageOrAnimation`.
     * @param settings A list of `Settings` to apply during the writing process. These settings define specific
     *                 configurations for handling the image or animation, including orientation processing modes.
     * @param out The output stream to which the image or animation will be written.
     */
    fun write(image: ImageOrAnimation, settings: List<Settings>, out: OutputStream){
        val orientationValue = image.originalMetadata?.findShortValue(TiffTag.TIFF_TAG_ORIENTATION)?.toInt()
        //No need to rotate anything
        if (orientationValue == null || !orientationValue.isRotatedOrientation()) {
            writeInternal(image, settings, out)
            return
        }
        val rotateSetting=settings.getSetting<ConvertSettings>()?: ConvertSettings()
        when(rotateSetting.originalOrientationProcessingMode){
            ConvertSettings.OriginalOrientationProcessingMode.DEFAULT->processOrientationDefaultMode(image, settings, out,orientationValue)
            ConvertSettings.OriginalOrientationProcessingMode.WRITE_EXIF_METADATA_TAG->rewriteOrientationTagToOutputStream(image, settings, out, orientationValue)
            ConvertSettings.OriginalOrientationProcessingMode.ROTATE_IMAGE->rotateAndWriteImageFile(orientationValue, image, settings, out)
            ConvertSettings.OriginalOrientationProcessingMode.IGNORE -> { writeInternal(image, settings, out) }
        }
    }

    fun processOrientationDefaultMode(
        image: ImageOrAnimation,
        settings: List<Settings>,
        out: OutputStream,
        orientationValue: Int
    ) {

        //Can't just set orientation tag, have to rotate image
        if (!getImageFormat().supportsKimMetadataWrite()) {
            rotateAndWriteImageFile(orientationValue, image, settings, out)
            return
        }
        //Set original orientation tag
        rewriteOrientationTagToOutputStream(image, settings, out, orientationValue)
    }

    /**
     * Rewrites the orientation metadata tag for an image or animation and outputs the modified data to the specified output stream.
     *
     * This function takes an input image or animation, applies specified settings, and writes the output with the updated orientation metadata.
     *
     * @param image The image or animation to process. It must be an instance of `ImageOrAnimation`.
     * @param settings A list of `Settings` to apply during the writing process.
     * @param out A supplier function that provides the `OutputStream` where the modified image data should be written.
     * @param orientationValue An integer representing the new orientation value to set in the image metadata.
     */
    fun ImageWriter.rewriteOrientationTagToOutputStream(
        image: ImageOrAnimation,
        settings: List<Settings>,
        out: OutputStream,
        orientationValue: Int
    ) {
        //Create wrapper for output stream
        val queueOutputStream = QueueOutputStream()
        val queueInputStream = queueOutputStream.newQueueInputStream()
        //write bytes there
        writeInternal(image, settings,queueOutputStream)
        val byteReader: ByteReader = JvmInputStreamByteReader(queueInputStream, queueInputStream.available().toLong())
        //set real output stream to write result
        val byteWriter = OutputStreamByteWriter(out)
        //Update metadata
        Kim.update(byteReader, byteWriter, MetadataUpdate.Orientation(TiffOrientation.of(orientationValue)!!))
    }

    /**
     * Rotates the specified image or animation to the correct orientation based on the given orientation value
     * and writes the resulting image to the provided output stream using the specified settings.
     *
     * @param orientationValue The orientation value representing how the image should be rotated or flipped.
     *                         Acceptable values range from 2 to 8:
     *                         - 2: Flip horizontally
     *                         - 3: Rotate 180 degrees clockwise
     *                         - 4: Flip vertically
     *                         - 5: Flip horizontally and rotate 270 degrees clockwise
     *                         - 6: Rotate 90 degrees clockwise
     *                         - 7: Flip horizontally and rotate 90 degrees clockwise
     *                         - 8: Rotate 270 degrees clockwise
     * @param image The image or animation to be rotated and written. It must be an instance of `ImageOrAnimation`.
     * @param settings A list of `Settings` to apply during the writing process, which define specific configurations.
     * @param out The output stream to which the rotated image or animation will be written.
     */
    fun ImageWriter.rotateAndWriteImageFile(
        orientationValue: Int,
        image: ImageOrAnimation,
        settings: List<Settings>,
        out: OutputStream
    ) {
        val rotatedImage = tryRotateImageToRightOrientation(orientationValue, image)
        writeInternal(rotatedImage, settings, out)
        return
    }

    /**
     * Writes an image or animation to the specified output stream using the provided settings.
     *
     * @param image The image or animation to be written. It must be an instance of `ImageOrAnimation`.
     * @param settings A list of `Settings` to apply during the writing process. These settings define specific
     *                 configurations for handling the image or animation.
     * @param out The output stream to which the image or animation will be written.
     */
    fun writeInternal(image: ImageOrAnimation, settings: List<Settings>, out: OutputStream)

    /**
     * Checks if the image writer supports animation.
     *
     * @return `true` if the image writer supports animation, `false` otherwise
     */
    fun supportsAnimation(): Boolean = false

    /**
     * Attempts to rotate the given image to the correct orientation based on the specified orientation value.
     * If the orientation is null or outside the range of 2-8, the original image is returned unaltered.
     *
     * @param orientation The orientation value representing how the image should be rotated or flipped.
     *                    Acceptable values range from 2 to 8:
     *                    - 2: Flip horizontal
     *                    - 3: Rotate 180 degrees clockwise
     *                    - 4: Flip vertical
     *                    - 5: Flip horizontal and rotate 270 degrees clockwise (90 degrees counterclockwise)
     *                    - 6: Rotate 90 degrees clockwise
     *                    - 7: Flip horizontal and rotate 90 degrees clockwise
     *                    - 8: Rotate 270 degrees clockwise
     * @param image The image or animation to be rotated or transformed. It must be an instance of `ImageOrAnimation`.
     * @return The rotated or transformed image as an instance of `ImageOrAnimation`.
     *         If an error occurs or the orientation is invalid, the original image is returned.
     */
    fun ImageWriter.tryRotateImageToRightOrientation(
        orientation: Int,
        image: ImageOrAnimation
    ): ImageOrAnimation {
        //Not rotated
        val time=System.currentTimeMillis()
        if ( !orientation.isRotatedOrientation() ) return image
        try {
            val settings = when (orientation) {
                2 -> listOf(FlipSettings(FlipSettings.Type.HORIZONTAL))// Flip Horizontal
                3 -> listOf(RotateSettings(180.0)) // Rotate 180 CW
                4 -> listOf(FlipSettings(FlipSettings.Type.VERTICAL)) // Flip Vertical
                5 -> listOf(FlipSettings(FlipSettings.Type.HORIZONTAL), RotateSettings(270.0)) // Flip Horizontal and Rotate 270 CW (90 CCW)
                6 -> listOf(RotateSettings(90.0)) // Rotate 90 CW
                7 -> listOf(FlipSettings(FlipSettings.Type.HORIZONTAL), RotateSettings(90.0)) // Flip Horizontal and Rotate 90 CW
                8 -> listOf(RotateSettings(270.0)) // Rotate 270 CW
                else -> listOf<TransformSettings>() // Default case
            }
            return settings.fold(image) { acc, setting -> setting.createTransformer().process(acc, listOf(setting)) }
        } catch (t: Throwable) {
            logger.log(Level.WARNING, "Error rotating image to right orientation $orientation ${t.javaClass}:${t.message}")
            return image
        } finally {
            logger.log(Level.FINEST,"Rotating image to original orientation took ${System.currentTimeMillis()-time}ms")
        }
    }
    fun Int?.isRotatedOrientation(): Boolean = this!=null && this in 2..8
    companion object {
        private val logger = Logger.getLogger(this::class.java.name)
    }
}

