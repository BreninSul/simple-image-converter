package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ResizeToSettings

/**
 * The `ResizeToTransformer` class is a concrete implementation of the
 * `PredefinedTransformer` class. It represents a transformer that resizes
 * an image to a specified resolution using the provided settings.
 *
 * @constructor Creates an instance of the `ResizeToTransformer` class.
 * @see [link](https://sksamuel.github.io/scrimage/resize/)
 */
open class ResizeToTransformer : PredefinedTransformer<ResizeToSettings>(ResizeToSettings::class) {
    override val name: String = "ResizeTo"

    override fun processTransformation(image: ImmutableImage, settings: ResizeToSettings): ImmutableImage = image.resizeTo(settings.resolution.width, settings.resolution.height, settings.position, settings.colour)
}
