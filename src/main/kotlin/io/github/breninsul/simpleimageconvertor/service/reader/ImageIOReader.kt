package io.github.breninsul.simpleimageconvertor.service.reader

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import java.awt.image.BufferedImage
import java.io.InputStream
import java.util.function.Supplier
import javax.imageio.ImageIO

open class ImageIOReader(private val order: Int = Int.MAX_VALUE) : ImageReader {
    protected open val supportedImageTypes = ImageIO.getReaderFormatNames().map { it.lowercase() }.toSet() + setOf("svg+xml")
    override fun supportedTypes() = supportedImageTypes
    override fun read(fileStream: Supplier<InputStream>, settings: List<Settings>): ImageOrAnimation {
        val bufferedImage: BufferedImage = fileStream.get().use { ImageIO.read(it) }
        val originalImage = ImmutableImage.fromAwt(bufferedImage)
        return ImageOrAnimation(null, originalImage)
    }


    override fun getOrder(): Int {
        return order
    }
}
