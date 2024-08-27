package io.github.breninsul.simpleimageconvertor.service.transformer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import java.awt.image.BufferedImage

/**
 * Represents an interface for image transformation operations that
 * implement the [ImageTransformer] interface. Provides a method to process
 * a static image using the provided settings. Also provides a method to
 * process an image using the provided settings.
 */
fun interface ImageIOTransformer : ImageTransformer {
    override fun processStatic(image: ImmutableImage, settings: List<Settings>): ImmutableImage {
        return ImmutableImage.fromAwt(processIO(image.awt()))
    }

    /**
     * Processes the given image using the provided settings.
     *
     * @param image The image to process. It must be an instance of
     *    [BufferedImage].
     * @return The processed image as an instance of [BufferedImage].
     */
    fun processIO(image: BufferedImage): BufferedImage
}
