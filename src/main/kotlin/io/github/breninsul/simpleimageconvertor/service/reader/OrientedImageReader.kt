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

package io.github.breninsul.simpleimageconvertor.service.reader

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.exif.ExifDirectoryBase
import com.drew.metadata.exif.ExifIFD0Directory
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.FlipSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.RotateSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TransformSettings
import java.io.InputStream
import java.util.function.Supplier
import java.util.logging.Level
import java.util.logging.Logger


/**
 * The ImageReader interface provides a way to read and convert images from
 * various file types.
 */
interface OrientedImageReader : ImageReader {

    override fun read(fileStream: Supplier<InputStream>, settings: List<Settings>): ImageOrAnimation {
        val image = readInternal(fileStream, settings)
        val orientation = readOrientation(fileStream)
        val processed = tryRotateImageToRightOrientation(orientation, image)
        return processed
    }

    fun readOrientation(
        fileStream: Supplier<InputStream>,
    ): Int? {
        val time=System.currentTimeMillis()
        try {
            val exifMetadata = fileStream.get().use { ImageMetadataReader.readMetadata(it) }?.getFirstDirectoryOfType(ExifDirectoryBase::class.java)
            return exifMetadata?.getInt(ExifIFD0Directory.TAG_ORIENTATION)
        } catch (t: Throwable) {
            logger.log(Level.WARNING, "Error read image orientation  ${t.javaClass}:${t.message}")
            return null
        } finally {
            logger.log(Level.FINEST,"Getting metadata for image took ${System.currentTimeMillis()-time}ms")
        }
    }

    fun tryRotateImageToRightOrientation(
        orientation: Int?,
        image: ImageOrAnimation
    ): ImageOrAnimation {
        //Not rotated
        val time=System.currentTimeMillis()
        if (orientation == null|| orientation < 2 || orientation > 8 ) return image
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


    fun readInternal(fileStream: Supplier<InputStream>, settings: List<Settings>): ImageOrAnimation

    companion object {
       private val logger = Logger.getLogger(this::class.java.name)
    }
}
