package io.github.breninsul.simpleimageconvertor.service.reader

import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay.GifSequenceReaderWithDelay
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import java.io.InputStream
import java.util.function.Supplier


open class GifReader(private val order:Int=1) : ImageReader {
    protected open val supportedImageTypes = setOf("gif")

    override fun read(fileStream: Supplier<InputStream>,settings: List<Settings>): ImageOrAnimation {
            val reader=fileStream.get().use {
                val reader = GifSequenceReaderWithDelay()
                reader.read(it)
                reader
            }
            val gif= AnimatedGifWithDelay(reader)
            return ImageOrAnimation(gif,null)
        }

    override fun supportedTypes(): Set<String> {
        return supportedImageTypes
    }

    override fun getOrder(): Int {
        return order
    }
}


