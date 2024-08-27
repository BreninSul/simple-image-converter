package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ResizeSettings

/**
 * The `ResizeTransformer` class is an implementation of the abstract class
 * `PredefinedTransformer`. It represents a transformer that resizes an
 * image based on the provided settings.
 *
 * @constructor Create a new instance of `ResizeTransformer`.
 * @property name The name of the transformer.
 * @see [link](https://sksamuel.github.io/scrimage/resize/)
 */
open class ResizeTransformer : PredefinedTransformer<ResizeSettings>(ResizeSettings::class) {
    override val name: String = "Resize"

    override fun processTransformation(image: ImmutableImage, settings: ResizeSettings): ImmutableImage = image.resize(settings.scaleFactor, settings.position, settings.colour)
}
