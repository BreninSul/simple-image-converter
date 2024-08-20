package io.github.breninsul.simpleimageconvertor.converter.writer

import com.sksamuel.scrimage.nio.GifSequenceWriter
import com.sksamuel.scrimage.nio.StreamingGifWriter
import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.ImageFormatEnum
import io.github.breninsul.simpleimageconvertor.dto.Settings
import java.awt.image.BufferedImage.TYPE_INT_ARGB
import java.io.OutputStream
import java.time.Duration
import java.util.function.Supplier
import javax.imageio.ImageIO

open class GifWriter : ImageWriter {
    open class FramesGifWriter(
        infiniteLoop:Boolean = true,
        compressed:Boolean = false
    ) : StreamingGifWriter(Duration.ofMillis(100),infiniteLoop,compressed) {

    }
    protected open val supportedImageTypes = setOf(ImageFormatEnum.GIF)

    override fun supportedTypes(): Set<ImageFormatEnum> {
        return supportedImageTypes
    }

    override fun write(image: ConvertableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        if (image.isAnimation()) {
            val decodedImage = image.animation!!
            out.get().use { outputStream ->
                outputStream.write(decodedImage.bytes)
            }
        } else {
            out.get().use { ImageIO.write(image.image!!.awt(), "gif", it) }
        }
    }

    override fun getOrder(): Int {
        return 1
    }

    override fun supportsAnimation(): Boolean = true
}