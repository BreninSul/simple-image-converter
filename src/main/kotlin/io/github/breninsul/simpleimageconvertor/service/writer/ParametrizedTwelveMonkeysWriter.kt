package io.github.breninsul.simpleimageconvertor.service.writer;

import com.sksamuel.scrimage.metadata.ImageMetadata
import java.awt.image.BufferedImage
import java.io.OutputStream
import javax.imageio.IIOImage
import javax.imageio.ImageIO
import javax.imageio.ImageWriteParam
import javax.imageio.ImageWriter

/**
 * Represents a class for writing images using the TwelveMonkeys library
 * with customizable parameters.
 *
 * @param format The format of the image to be written.
 * @constructor Creates a new instance of the
 *    ParametrizedTwelveMonkeysWriter class with the given format.
 * @see ImageWriter
 * @see ImageWriteParam
 * @see BufferedImage
 * @see ImageMetadata
 * @see OutputStream
 */
open class ParametrizedTwelveMonkeysWriter(val format: String) {

    open fun write(image: BufferedImage, metadata: ImageMetadata?, outStream: OutputStream, params: ImageWriteParam?) {
        val writer = imageWriter()
        ImageIO.createImageOutputStream(outStream).use { ios ->
            writer.output = ios
            writer.write(null, IIOImage(image, null, null), params ?: defaultParams(writer))
            writer.dispose()
        }
    }

    protected open fun defaultParams(writer: ImageWriter): ImageWriteParam = writer.defaultWriteParam

    protected open fun imageWriter(): ImageWriter = ImageIO.getImageWritersByFormatName(format).next()
}