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

package io.github.breninsul.simpleimageconvertor.service.convert

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.getSetting
import io.github.breninsul.simpleimageconvertor.dto.writer.ConvertSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageConvertingException
import io.github.breninsul.simpleimageconvertor.exception.ImageException
import io.github.breninsul.simpleimageconvertor.exception.ImageWritingException
import io.github.breninsul.simpleimageconvertor.service.writer.*
import java.io.OutputStream
import java.util.function.Supplier
import java.util.logging.Level
import java.util.logging.Logger

open class DefaultImageConverter(
    writers: List<ImageWriter> = listOf(
        BmpWriter(),
        GifWriter(),
        IffWriter(),
        JpegWriter(),
        PictWriter(),
        PngWriter(),
        PnmWriter(),
        TgaWriter(),
        TiffWriter(),
        WbmpWriter(),
        WebpWriter(),
        PsdWriter(),
        IcnsWriter(),
        IcoWriter(),
    ).let { it + PdfWriter(it) }
) : ImageConverter {
    protected open val imageWriters: List<ImageWriter> = writers.sortedBy { it.getOrder() }

    override fun convert(
        image: ImageOrAnimation,
        settings: List<Settings>,
        outputSupplier: Supplier<OutputStream>
    ) {
        try {
            val setting = settings.getConvertSetting()
            val writer = getWriter(setting)
            writer.write(image, settings, outputSupplier)
        } catch (e: Exception) {
            logger.log(Level.WARNING, "Error writing image", e)
            throw if (e is ImageException) e else ImageWritingException(e.message, e)
        }
    }

    override fun checkSettings(settings: List<Settings>) {
        settings.getConvertSetting()
    }

    protected open fun List<Settings>.getConvertSetting() = this.getSetting<ConvertSettings>() ?: throw ImageConvertingException("No converter settings provided")
    open fun supportedTypes(): Set<ImageFormat> = imageWriters.map { it.supportedTypes() }.flatten().toSet()

    protected open fun getWriter(setting: ConvertSettings) = imageWriters.firstOrNull { it.supports(setting.format) } ?: throw ImageConvertingException("No writer exist for ${setting.format}")

    companion object {
        val logger = Logger.getLogger(this::class.java.name)
    }
}