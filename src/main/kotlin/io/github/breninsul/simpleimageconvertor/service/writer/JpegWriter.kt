package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.*
import io.github.breninsul.simpleimageconvertor.dto.writer.JpegWriterSettings
import java.io.OutputStream
import java.util.function.Supplier

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