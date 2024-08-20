package io.github.breninsul.simpleimageconvertor.converter.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.*
import java.io.OutputStream
import java.util.function.Supplier

open class IffWriter : StaticImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormatEnum.IFF)

    override fun supportedTypes(): Set<ImageFormatEnum> {
        return supportedImageTypes
    }
    override fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        val writer=com.sksamuel.scrimage.nio.IffWriter()
        writer.write(image,image.metadata,out.get())
    }

    override fun getOrder(): Int {
        return 1
    }

}