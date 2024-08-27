package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.getSetting
import io.github.breninsul.simpleimageconvertor.dto.writer.ImageIOWriterSettings
import java.io.OutputStream
import java.util.function.Supplier

/**
 * Represents an abstract class for writing images using the TwelveMonkeys
 * library. This class implements the StaticImageWriter interface
 * and provides common functionality for writing static images.
 *
 * @param supportedImageTypes The set of ImageFormats supported by this
 *    writer.
 * @param writerFormatName The format name of the writer.
 * @param order The order of the writer. Default value is 1.
 * @param delegatedWriter The ParametrizedTwelveMonkeysWriter instance to
 *    delegate the writing operation to. Default value is
 *    ParametrizedTwelveMonkeysWriter(writerFormatName).
 * @see ImageFormat
 * @see ParametrizedTwelveMonkeysWriter
 * @see StaticImageWriter
 */
open class AbstractTwelveMonkeysWriterWriter(
    protected open val supportedImageTypes: Set<ImageFormat>,
    protected open val writerFormatName: String,
    private val order: Int = 1,
    protected open val delegatedWriter: ParametrizedTwelveMonkeysWriter = ParametrizedTwelveMonkeysWriter(writerFormatName)
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