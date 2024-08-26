package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.BrightnessSettings

/**
 * The `BrightnessTransformer` class is a concrete implementation of the abstract class `PredefinedTransformer`. It represents a transformer that adjusts the brightness of an image
 *  using the provided settings.
 *
 * @constructor Creates a `BrightnessTransformer` instance.
 *
 * @property name The name of the brightness transformer.
 *
 * @param T The type of the transform settings. It must be a subclass of `BrightnessSettings`.
 *
 * @see PredefinedTransformer
 * @see BrightnessSettings
 *
 * @see [link](https://sksamuel.github.io/scrimage/brightness/)
 */
open class BrightnessTransformer : PredefinedTransformer<BrightnessSettings>(BrightnessSettings::class) {
    override val name: String = "Brightness"

    override fun processTransformation(image: ImmutableImage, settings: BrightnessSettings): ImmutableImage = image.brightness(settings.factor)
}
