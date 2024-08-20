package io.github.breninsul.simpleimageconvertor.converter.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.*
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.util.function.Supplier


open class PdfWriter(
    protected open val writers: List<ImageWriter>
) : ImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormatEnum.WEBP)

    override fun supportedTypes(): Set<ImageFormatEnum> {
        return supportedImageTypes
    }

    override fun write(image: ConvertableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        val document = PDDocument()
        val setting=settings.getSetting<PdfWriterSettings>()?:PdfWriterSettings()
        if (setting.imageFormat == ImageFormatEnum.PDF) throw IllegalStateException("Unsupported format ${setting.imageFormat}")
        val writer = writers.firstOrNull { it.supportedTypes().contains(setting.imageFormat) } ?: throw IllegalStateException("Unsupported format ${setting.imageFormat}")
        if (image.isAnimation()) {
            image.animation!!.frames.forEach {
                writePage(setting, writer, it, settings, document)
            }
        } else {
            writePage(setting, writer, image.image!!, settings, document)
        }
        out.get().use { document.save(it) }
    }

    protected open fun writePage(
        pdfSetting: PdfWriterSettings,
        writer: ImageWriter,
        image: ImmutableImage,
        settings: List<Settings>,
        document: PDDocument
    ) {
        val rectangle = pdfSetting.rectangle
        val page = PDPage(rectangle);
        val outputStream = ByteArrayOutputStream()
        writer.write(ConvertableImage(null, image), settings) { outputStream }
        val pdfImage = PDImageXObject.createFromByteArray(document, outputStream.toByteArray(), null)
        val contents = PDPageContentStream(document, page, pdfSetting.appendMode, pdfSetting.compress);
        contents.drawImage(pdfImage, 0F, 0F, rectangle.width, rectangle.height)
    }

    override fun getOrder(): Int {
        return 1
    }

    override fun supportsAnimation(): Boolean = true
}