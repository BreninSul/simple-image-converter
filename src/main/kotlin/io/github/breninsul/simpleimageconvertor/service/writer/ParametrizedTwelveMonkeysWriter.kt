package io.github.breninsul.simpleimageconvertor.service.writer;

import com.sksamuel.scrimage.AwtImage
import com.sksamuel.scrimage.metadata.ImageMetadata
import java.awt.image.BufferedImage
import java.io.OutputStream
import javax.imageio.IIOImage
import javax.imageio.ImageIO
import javax.imageio.ImageWriteParam
import javax.imageio.ImageWriter

open class ParametrizedTwelveMonkeysWriter(val format:String)  {

    open fun write(image: BufferedImage, metadata: ImageMetadata?, outStream: OutputStream, params: ImageWriteParam?) {
        val writer = imageWriter()
        ImageIO.createImageOutputStream(outStream).use {ios->
            writer.output = ios
            writer.write(null, IIOImage(image, null, null), params ?: defaultParams(writer))
            writer.dispose()
        }
    }

    protected open fun defaultParams(writer: ImageWriter): ImageWriteParam = writer.defaultWriteParam

    protected open fun imageWriter(): ImageWriter = ImageIO.getImageWritersByFormatName(format).next()
}