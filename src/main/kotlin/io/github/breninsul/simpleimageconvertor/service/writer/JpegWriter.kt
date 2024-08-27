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

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.getSetting
import io.github.breninsul.simpleimageconvertor.dto.writer.JpegWriterSettings
import java.io.OutputStream
import java.util.function.Supplier

/**
 * The `JpegWriter` class is an implementation of the `StaticImageWriter`
 * interface for writing JPEG images. It provides a method to write a
 * static image to an output stream using the specified settings. The
 * class overrides the `supportedTypes` from the `StaticImageWriter`
 * superclass to specify that it supports the JPEG image format. The class
 * also overrides the `getOrder` method to specify the order in which this
 * writer should be used in an image conversion process.
 *
 * @see StaticImageWriter
 * @see ImageFormat
 * @see Settings
 * @see JpegWriterSettings
 * @see com.sksamuel.scrimage.nio.JpegWriter
 */
open class JpegWriter : StaticImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormat.JPEG)

    override fun supportedTypes(): Set<ImageFormat> {
        return supportedImageTypes
    }

    override fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        val setting = settings.getSetting<JpegWriterSettings>()
        val writer = setting?.let { com.sksamuel.scrimage.nio.JpegWriter(it.compressionLevel, it.progressive) } ?: com.sksamuel.scrimage.nio.JpegWriter()
        writer.write(image, image.metadata, out.get())
    }


    override fun getOrder(): Int {
        return 1
    }

}