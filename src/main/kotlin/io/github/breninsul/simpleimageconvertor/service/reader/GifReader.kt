package io.github.breninsul.simpleimageconvertor.service.reader

import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay.GifSequenceReaderWithDelay
import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.Settings
import java.io.InputStream
import java.util.function.Supplier


open class GifReader(private val order:Int=1) : ImageReader {
    protected open val supportedImageTypes = setOf("gif")

    override fun read(fileStream: Supplier<InputStream>,settings: List<Settings>): ConvertableImage {
            val reader=fileStream.get().use {
                val reader = GifSequenceReaderWithDelay()
                reader.read(it)
                reader
            }
            val gif= AnimatedGifWithDelay(reader)
            return ConvertableImage(gif,null)
        }

    override fun supportedTypes(): Set<String> {
        return supportedImageTypes
    }

    override fun getOrder(): Int {
        return order
    }
}


