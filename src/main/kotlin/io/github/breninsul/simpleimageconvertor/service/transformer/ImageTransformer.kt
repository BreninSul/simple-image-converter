package io.github.breninsul.simpleimageconvertor.service.transformer

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay
import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.Settings
import io.github.breninsul.simpleimageconvertor.service.transformer.ScaleTransformer.Companion.logger
import java.util.logging.Level

/** Represents an interface for image transformation operations. */
fun interface ImageTransformer {
    /**
     * The `name` property represents the name of an image transformer. It is a
     * string value.
     */
    val name: String get() = "Functional"

    /**
     * Processes a static image using the provided settings.
     *
     * @param image The static image to process. It must be an instance of [ImmutableImage].
     * @param settings The list of settings to apply during image processing. Each setting must implement the [Settings] interface.
     * @return The processed static image as an instance of [ImmutableImage].
     */
    fun processStatic(image: ImmutableImage, settings: List<Settings>): ImmutableImage

    /**
     * Process the animation frames of an AnimatedGifWithDelay object using the provided settings.
     *
     * @param settings The list of settings to apply during image processing. Each setting must implement the [Settings] interface.
     * @return The processed AnimatedGifWithDelay object with updated animation frames as instances of [BufferedImage].
     */
    fun AnimatedGifWithDelay.processAnimation(settings: List<Settings>): AnimatedGifWithDelay {
        return this.mapFrames { i -> processStatic(i.toImmutableImage(), settings).awt() }
    }

    /**
     * Processes an image using the provided settings.
     *
     * @param image The image to process. It must be an instance of [ConvertableImage].
     * @param settings The list of settings to apply during image processing. Each setting must implement the [Settings] interface.
     * @return The processed image as an instance of [ConvertableImage].
     * @throws Exception if an error occurs during image processing.
     */
    fun process(image: ConvertableImage, settings: List<Settings> = listOf()): ConvertableImage {
        try {
            val processed = if (image.isAnimation()) {
                ConvertableImage(image.animation!!.processAnimation(settings), null)
            } else {
                ConvertableImage(null, processStatic(image.image!!, settings))
            }
            return processed
        } catch (e: Exception) {
            logger.log(Level.WARNING, "Error processing image", e)
            throw e
        }
    }

    /**
     * Checks if the list of [Settings] supports the given operation.
     *
     * @return `true` if all settings in the list support the operation, `false` otherwise.
     */
    fun List<Settings>.supports(): Boolean =true
}
