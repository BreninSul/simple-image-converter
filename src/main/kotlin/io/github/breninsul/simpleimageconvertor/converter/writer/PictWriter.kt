package io.github.breninsul.simpleimageconvertor.converter.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.*
import java.io.OutputStream
import java.util.function.Supplier

open class PictWriter : StaticImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormatEnum.PICT)

    override fun supportedTypes(): Set<ImageFormatEnum> {
        return supportedImageTypes
    }
    override fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        val writer=com.sksamuel.scrimage.nio.PictWriter()
        writer.write(image,image.metadata,out.get())
    }

    override fun getOrder(): Int {
        return 1
    }

}