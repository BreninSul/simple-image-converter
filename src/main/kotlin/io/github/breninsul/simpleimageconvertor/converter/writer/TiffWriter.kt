package io.github.breninsul.simpleimageconvertor.converter.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.*
import java.io.OutputStream
import java.util.function.Supplier

open class TiffWriter : StaticImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormatEnum.TIFF)

    override fun supportedTypes(): Set<ImageFormatEnum> {
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