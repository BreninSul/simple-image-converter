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

import com.sksamuel.scrimage.metadata.ImageMetadata
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import java.awt.image.BufferedImage
import java.io.OutputStream
import javax.imageio.ImageWriteParam
import javax.imageio.ImageWriter

/**
 * Represents a class for writing ICO (Windows Icon) images.
 *
 * This class extends the AbstractTwelveMonkeysWriterWriter class and
 * provides ICO-specific functionality for writing images.
 *
 * @param order The order of the writer. The default value is 1.
 * @see AbstractTwelveMonkeysWriterWriter
 * @see ImageWriter
 * @see ImageWriteParam
 */
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