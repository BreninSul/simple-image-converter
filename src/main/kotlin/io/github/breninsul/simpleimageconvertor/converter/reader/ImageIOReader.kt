package io.github.breninsul.simpleimageconvertor.converter.reader

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.Settings
import org.springframework.core.Ordered.LOWEST_PRECEDENCE
import java.awt.image.BufferedImage
import java.io.InputStream
import java.util.function.Supplier
import javax.imageio.ImageIO

open class ImageIOReader(private val order:Int=LOWEST_PRECEDENCE) : ImageReader {
    protected open val supportedImageTypes =ImageIO.getReaderFormatNames().map { it.lowercase() }.toSet()
    override fun supportedTypes()=supportedImageTypes
    override fun read(fileStream: Supplier<InputStream>,settings: List<Settings>): ConvertableImage {
        val bufferedImage: BufferedImage = fileStream.get().use { ImageIO.read(it) }
        val originalImage = ImmutableImage.fromAwt(bufferedImage)
        return ConvertableImage(null,originalImage)
    }



    override fun getOrder(): Int {
       return order
    }
}
