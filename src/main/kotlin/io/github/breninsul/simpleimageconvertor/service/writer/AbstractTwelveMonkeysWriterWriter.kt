package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.*
import io.github.breninsul.simpleimageconvertor.dto.writer.ImageIOWriterSettings
import java.io.OutputStream
import java.util.function.Supplier

open class AbstractTwelveMonkeysWriterWriter(
    protected open val supportedImageTypes : Set<ImageFormat>,
    protected open val writerFormatName : String,
    private val order:Int = 1,
    protected open val delegatedWriter:ParametrizedTwelveMonkeysWriter =  ParametrizedTwelveMonkeysWriter(writerFormatName)
) : StaticImageWriter {

    override fun supportedTypes(): Set<ImageFormat> {
        return supportedImageTypes
    }
    override fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        val params = settings.getSetting<ImageIOWriterSettings>()
        delegatedWriter.write(image.awt(), image.metadata, out.get(), params)
    }

    override fun getOrder(): Int {
        return order
    }

}