package io.github.breninsul.simpleimageconvertor.converter.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.*
import java.io.OutputStream
import java.util.function.Supplier

open class PngWriter : StaticImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormatEnum.PNG)

    override fun supportedTypes(): Set<ImageFormatEnum> {
        return supportedImageTypes
    }
    override fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        val setting=settings.getSetting<PngWriterSettings>()
        val writer=setting?.let {  com.sksamuel.scrimage.nio.PngWriter(it.compressionLevel)}?:com.sksamuel.scrimage.nio.PngWriter()
        writer.write(image,image.metadata,out.get())
    }


    override fun getOrder(): Int {
        return 1
    }

}