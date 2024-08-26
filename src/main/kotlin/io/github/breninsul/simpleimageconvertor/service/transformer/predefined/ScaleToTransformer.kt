package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleToSettings

/**
 * The `ScaleToTransformer` class is a subclass of `PredefinedTransformer` that represents a transformer for scaling images
 * using the provided `ScaleSettings`.
 *
 * @property name The name of the scale transformer.
 * @constructor Creates a `ScaleToTransformer` instance.
 *
 * @see [link](https://sksamuel.github.io/scrimage/scale/)
 *
 */
open class ScaleToTransformer : PredefinedTransformer<ScaleToSettings>(ScaleToSettings::class) {
    override val name: String = "ScaleTo"

    override fun processTransformation(image: ImmutableImage, settings: ScaleToSettings): ImmutableImage = image.scaleTo(settings.resolution.width, settings.resolution.height, settings.scaleMethod)
}
