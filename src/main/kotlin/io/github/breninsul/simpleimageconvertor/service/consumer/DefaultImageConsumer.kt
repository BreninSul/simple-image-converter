package io.github.breninsul.simpleimageconvertor.service.consumer

import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.exception.ImageException
import io.github.breninsul.simpleimageconvertor.exception.ImageReadingException
import io.github.breninsul.simpleimageconvertor.service.reader.*
import org.apache.tika.Tika
import java.io.InputStream
import java.util.function.Supplier
import java.util.logging.Level
import java.util.logging.Logger

open class  DefaultImageConsumer(
    protected open val tika:Tika=Tika(),
    readers:List<ImageReader> = listOf(WebpReader(), PngReader(), PdfReader(), GifReader(), ImageIOReader()),
    ):ImageConsumer {
    protected open val imageReaders:List<ImageReader> = readers.sortedBy { it.getOrder() }
    override fun read(
        inputStreamSupplier: Supplier<InputStream>,
        settings: List<Settings>,
        mimeType: String?,
    ): ImageOrAnimation {
        try {
            val resolvedMimeType: String = mimeType?:inputStreamSupplier.get().use { tika.detect(it) }
            val reader = getSuitableReader(resolvedMimeType)
            val originalImage=reader.read(inputStreamSupplier,settings)
            return originalImage
        } catch (e: Exception) {
            logger.log(Level.WARNING,"Error reading image", e)
            throw if (e is ImageException)  e else ImageReadingException(e.message,e)
        }
    }
    open fun supportedTypes():Set<String> = imageReaders.map { it.supportedTypes() }.flatten().toSet()
    protected open fun getSuitableReader(mimeType: String) = imageReaders.firstOrNull { it.supports(mimeType) }?:throw ImageReadingException("No Suitable Reader for $mimeType")


    companion object {
        val logger = Logger.getLogger(this::class.java.name)
    }
}
