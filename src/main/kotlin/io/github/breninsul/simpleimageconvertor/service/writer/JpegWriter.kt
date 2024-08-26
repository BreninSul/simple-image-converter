package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.*
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.getSetting
import io.github.breninsul.simpleimageconvertor.dto.writer.JpegWriterSettings
import java.io.OutputStream
import java.util.function.Supplier

/**
 * The `JpegWriter` class is an implementation of the `StaticImageWriter` interface for writing JPEG images.
 * It provides a method to write a static image to an output stream using the specified settings.
 * The class overrides the `supportedTypes` from the `StaticImageWriter` superclass to specify that it supports the JPEG image format.
 * The class also overrides the `getOrder` method to specify the order in which this writer should be used in an image conversion process.
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
        val setting=settings.getSetting<JpegWriterSettings>()
        val writer=setting?.let {  com.sksamuel.scrimage.nio.JpegWriter(it.compressionLevel,it.progressive)}?:com.sksamuel.scrimage.nio.JpegWriter()
        writer.write(image,image.metadata,out.get())
    }


    override fun getOrder(): Int {
        return 1
    }

}