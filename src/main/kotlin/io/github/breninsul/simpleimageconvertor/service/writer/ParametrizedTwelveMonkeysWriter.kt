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