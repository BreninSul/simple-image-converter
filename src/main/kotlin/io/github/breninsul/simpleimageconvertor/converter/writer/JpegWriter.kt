package io.github.breninsul.simpleimageconvertor.converter.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.*
import java.io.OutputStream
import java.util.function.Supplier

open class JpegWriter : StaticImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormatEnum.JPEG)

    override fun supportedTypes(): Set<ImageFormatEnum> {
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