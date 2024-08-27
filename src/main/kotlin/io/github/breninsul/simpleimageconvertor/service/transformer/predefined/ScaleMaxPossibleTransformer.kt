package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleToSettings

/**
 * The `ScaleMaxPossibleTransformer` class is a concrete implementation of
 * the `PredefinedTransformer` abstract class. It scales an image to the
 * maximum possible size based on the provided scale settings.
 *
 * @property name The name of the scale transformer.
 * @see [link](https://sksamuel.github.io/scrimage/max/)
 */
open class ScaleMaxPossibleTransformer : PredefinedTransformer<ScaleToSettings>(ScaleToSettings::class) {
    override val name: String = "ScaleMaxPossible"

    override fun processTransformation(image: ImmutableImage, settings: ScaleToSettings): ImmutableImage = image.max(settings.resolution.width, settings.resolution.height, settings.scaleMethod)
}
