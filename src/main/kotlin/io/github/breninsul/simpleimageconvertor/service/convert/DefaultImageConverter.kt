package io.github.breninsul.simpleimageconvertor.service.convert

import com.sksamuel.scrimage.nio.PcxWriter
import io.github.breninsul.simpleimageconvertor.dto.ConvertSettings
import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.Settings
import io.github.breninsul.simpleimageconvertor.dto.getSetting
import io.github.breninsul.simpleimageconvertor.exception.ImageConvertingException
import io.github.breninsul.simpleimageconvertor.exception.ImageException
import io.github.breninsul.simpleimageconvertor.exception.ImageWritingException
import io.github.breninsul.simpleimageconvertor.service.writer.*
import java.io.OutputStream
import java.util.function.Supplier
import java.util.logging.Level
import java.util.logging.Logger

open class DefaultImageConverter(
    protected open val writers: List<ImageWriter> = listOf(
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
        WebpWriter()
    ).let { it + PdfWriter(it) }
) : ImageConverter {

    override fun convert(
        image: ConvertableImage,
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

    override fun checkSettings(settings:List<Settings>) {
        settings.getConvertSetting()
    }

    protected open  fun List<Settings>.getConvertSetting() = this.getSetting<ConvertSettings>() ?: throw ImageConvertingException("No converter settings provided")

    protected open fun getWriter(setting: ConvertSettings) = writers.firstOrNull { it.supports(setting.format) } ?: throw ImageConvertingException("No writer exist for ${setting.format}")
    companion object {
        val logger = Logger.getLogger(this::class.java.name)
    }
}