package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.*
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.getSetting
import io.github.breninsul.simpleimageconvertor.dto.writer.PngWriterSettings
import java.io.OutputStream
import java.util.function.Supplier

open class PngWriter : StaticImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormat.PNG)

    override fun supportedTypes(): Set<ImageFormat> {
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