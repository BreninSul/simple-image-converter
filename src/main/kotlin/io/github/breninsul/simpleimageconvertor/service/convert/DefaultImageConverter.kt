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