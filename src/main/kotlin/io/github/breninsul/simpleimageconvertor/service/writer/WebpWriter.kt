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
import com.sksamuel.scrimage.nio.AnimatedGif
import com.sksamuel.scrimage.webp.Gif2WebpWriter
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.getSetting
import io.github.breninsul.simpleimageconvertor.dto.writer.WebpWriterSettings
import java.io.OutputStream
import java.util.function.Supplier

/**
 * The WebpWriter class is an implementation of the AnimationImageWriter
 * interface for writing animated WebP images. It supports
 * writing both animation and static images in the WebP format.
 *
 * @constructor Creates a new instance of the WebpWriter class.
 * @see AnimationImageWriter
 */
open class WebpWriter : AnimationImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormat.WEBP)

    override fun supportedTypes(): Set<ImageFormat> {
        return supportedImageTypes
    }

    override fun writeAnimation(animation: AnimatedGif, settings: List<Settings>, out: Supplier<OutputStream>) {
        val webpSetting = settings.getSetting<WebpWriterSettings>()
        val delegate = webpSetting?.let { Gif2WebpWriter(it.q, it.m, !it.lossless) } ?: Gif2WebpWriter()
        out.get().use { delegate.write(animation, it) }
    }

    override fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        val webpSetting = settings.getSetting<WebpWriterSettings>()
        val delegate = webpSetting?.let { com.sksamuel.scrimage.webp.WebpWriter(it.z, it.q, it.m, it.lossless, it.noAlpha, it.multiThread) } ?: com.sksamuel.scrimage.webp.WebpWriter()
        out.get().use { delegate.write(image, image.metadata, it) }
    }

    override fun getOrder(): Int {
        return 1
    }

    override fun supportsAnimation(): Boolean = true
}