package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.*
import io.github.breninsul.simpleimageconvertor.dto.writer.TiffWriterSettings
import java.io.OutputStream
import java.util.function.Supplier

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