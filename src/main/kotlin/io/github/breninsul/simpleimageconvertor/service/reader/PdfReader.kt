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

import com.madgag.gif.fmsware.AnimatedGifEncoder
import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay.GifSequenceReaderWithDelay
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.reader.PdfReaderSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.getSetting
import io.github.breninsul.simpleimageconvertor.exception.ImageReadingException
import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.PDFRenderer
import java.awt.image.BufferedImage
import java.io.InputStream
import java.nio.file.Files
import java.util.function.Supplier
import kotlin.io.path.deleteIfExists
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream


open class PdfReader(private val order: Int = 1) : ImageReader {
    protected open val supportedImageTypes = setOf("pdf")
    override fun supportedTypes() = supportedImageTypes
    override fun read(fileStream: Supplier<InputStream>, settings: List<Settings>): ImageOrAnimation {
        val document: PDDocument = fileStream.get().use { Loader.loadPDF(it.readAllBytes()) }
        val setting = settings.getSetting<PdfReaderSettings>() ?: PdfReaderSettings()
        val pdfRenderer = PDFRenderer(document)
        val numberOfPages = document.numberOfPages
        val count = document.pages.count
        if (numberOfPages < 1) {
            throw ImageReadingException("No pages!")
        }
        val firstImage: BufferedImage = pdfRenderer.renderImage(0, setting.scale, setting.imageType, setting.destination)
        if (numberOfPages == 1) {
            val originalImage = ImmutableImage.fromAwt(firstImage)
            return ImageOrAnimation(null, originalImage)
        } else {
            val encoder = AnimatedGifEncoder()
            val tempFile = Files.createTempFile("AnimationReaderPdf", ".gif")
            tempFile.outputStream().use { outputStream ->
                encoder.start(outputStream)
                encoder.setRepeat(1)
                encoder.setSize(firstImage.width, firstImage.height)
                (0..<numberOfPages).forEach { index ->
                    encoder.setDelay(setting.animationDelay.toMillis().toInt())
                    val frame = if (index == 0) firstImage else pdfRenderer.renderImage(index, setting.scale, setting.imageType, setting.destination)
                    encoder.addFrame(frame)
                }
            }
            encoder.finish()
            val reader = tempFile.inputStream().use {
                val reader = GifSequenceReaderWithDelay()
                reader.read(it)
                reader
            }
            tempFile.deleteIfExists()
            val gif = AnimatedGifWithDelay(reader)
            return ImageOrAnimation(gif, null)
        }
    }

    override fun getOrder(): Int {
        return order
    }
}
