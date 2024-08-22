package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.metadata.ImageMetadata
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import java.awt.image.BufferedImage
import java.io.OutputStream
import javax.imageio.ImageWriteParam
import javax.imageio.ImageWriter


open class IcoWriter(order: Int = 1) : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.ICO), "ico", order,
    object : ParametrizedTwelveMonkeysWriter("ico") {
        override fun defaultParams(writer: ImageWriter): ImageWriteParam {
            val defaultParams = super.defaultParams(writer)
            defaultParams.compressionMode = ImageWriteParam.MODE_EXPLICIT
            return defaultParams
        }

        override fun write(image: BufferedImage, metadata: ImageMetadata?, outStream: OutputStream, params: ImageWriteParam?) {
            val convertedType = if (image.type != BufferedImage.TYPE_4BYTE_ABGR) {
                val convertedImg = BufferedImage(image.width, image.height, BufferedImage.TYPE_4BYTE_ABGR)
                convertedImg.graphics.drawImage(image, 0, 0, null)
                convertedImg
            } else image
            super.write(convertedType, metadata, outStream, params)
        }
    })