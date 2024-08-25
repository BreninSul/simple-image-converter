package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.AnimatedGif
import io.github.breninsul.simpleimageconvertor.dto.*
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.getSetting
import io.github.breninsul.simpleimageconvertor.dto.writer.PdfWriterSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageWritingException
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.util.function.Supplier


open class PdfWriter(
    protected open val writers: List<ImageWriter>
) : AnimationImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormat.PDF)

    override fun supportedTypes(): Set<ImageFormat> {
        return supportedImageTypes
    }

    override fun writeAnimation(animation: AnimatedGif, settings: List<Settings>, out: Supplier<OutputStream>) {
        val document = PDDocument()
        val setting = settings.getSetting<PdfWriterSettings>() ?: PdfWriterSettings()
        val writer = getImageWriter(setting)
        animation.frames.forEach {
            writePage(setting, writer, it, settings, document)
        }
        out.get().use {  document.save(it)}
    }


    override fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        val document = PDDocument()
        val setting = settings.getSetting<PdfWriterSettings>() ?: PdfWriterSettings()
        val writer = getImageWriter(setting)
        writePage(setting, writer, image, settings, document)
        out.get().use {  document.save(it)}
    }

    protected open fun getImageWriter(setting: PdfWriterSettings) = writers.firstOrNull { it.supportedTypes().contains(setting.imageFormat) } ?: throw ImageWritingException("Unsupported format ${setting.imageFormat}")

    protected open fun writePage(
        pdfSetting: PdfWriterSettings,
        writer: ImageWriter,
        image: ImmutableImage,
        settings: List<Settings>,
        document: PDDocument
    ) {
        val rectangle = pdfSetting.rectangle
        val page = PDPage(rectangle);
        document.addPage(page)
        val outputStream = ByteArrayOutputStream()
        writer.write(ImageOrAnimation(null, image), settings) { outputStream }
        val pdfImage = PDImageXObject.createFromByteArray(document, outputStream.toByteArray(), null)
        PDPageContentStream(document, page, pdfSetting.appendMode, pdfSetting.compress).use {contents->
            contents.drawImage(pdfImage, 0F, 0F, rectangle.width, rectangle.height)
        }
    }

    override fun getOrder(): Int {
        return 1
    }

    override fun supportsAnimation(): Boolean = true
}