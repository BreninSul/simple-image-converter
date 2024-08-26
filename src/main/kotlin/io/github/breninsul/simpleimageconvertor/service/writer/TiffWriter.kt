package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.*
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.getSetting
import io.github.breninsul.simpleimageconvertor.dto.writer.TiffWriterSettings
import java.io.OutputStream
import java.util.function.Supplier

/**
 * The `TiffWriter` class is a concrete implementation of the `StaticImageWriter` interface.
 * It provides functionality to write static images in the TIFF format.
 *
 * @property supportedImageTypes The set of supported image formats, which includes only the TIFF format.
 *
 * @see StaticImageWriter
 */
open class TiffWriter : StaticImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormat.TIFF)

    override fun supportedTypes(): Set<ImageFormat> {
        return supportedImageTypes
    }
    override fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        val setting=settings.getSetting<TiffWriterSettings>()
        val writer=setting?.let {  com.sksamuel.scrimage.nio.TiffWriter(it.compressionType)}?:com.sksamuel.scrimage.nio.TiffWriter()
        writer.write(image,image.metadata,out.get())
    }

    override fun getOrder(): Int {
        return 1
    }

}