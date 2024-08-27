package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.AnimatedGif
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import java.io.OutputStream
import java.util.function.Supplier
import javax.imageio.ImageIO

/**
 * GifWriter is a class that extends the AnimationImageWriter interface and
 * represents a GIF image writer. It provides functionality for writing
 * animated GIF images.
 *
 * @property supportedImageTypes A set of supported image formats, which in
 *    this case is only GIF.
 * @see AnimationImageWriter
 * @see ImageFormat
 */
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