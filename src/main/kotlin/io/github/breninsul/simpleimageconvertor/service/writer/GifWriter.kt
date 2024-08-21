package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.AnimatedGif
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.Settings
import java.io.OutputStream
import java.util.function.Supplier
import javax.imageio.ImageIO

open class GifWriter : AnimationImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormat.GIF)

    override fun supportedTypes(): Set<ImageFormat> {
        return supportedImageTypes
    }

    override fun writeAnimation(animation: AnimatedGif, settings: List<Settings>, out: Supplier<OutputStream>) {
        out.get().use { outputStream ->
            outputStream.write(animation.bytes)
        }
    }

    override fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        out.get().use { ImageIO.write(image.awt(), "gif", it) }
    }

    override fun getOrder(): Int {
        return 1
    }

    override fun supportsAnimation(): Boolean = true
}