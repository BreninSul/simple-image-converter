package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleSettings

/**
 * The `ScaleTransformer` class is a subclass of `PredefinedTransformer`
 * that represents a transformer for scaling images using the provided
 * `ScaleSettings`.
 *
 * @constructor Creates a `ScaleTransformer` instance.
 * @property name The name of the scale transformer.
 * @see [link](https://sksamuel.github.io/scrimage/scale/)
 */
open class ScaleTransformer : PredefinedTransformer<ScaleSettings>(ScaleSettings::class) {
    override val name: String = "Scale"

    override fun processTransformation(image: ImmutableImage, settings: ScaleSettings): ImmutableImage = image.scale(settings.scaleFactor, settings.scaleMethod)
}
