package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.BrightnessTransformer

/**
 * The `BrightnessSettings` class represents the settings for adjusting the brightness of an image.
 *
 * @constructor Creates a `BrightnessSettings` instance with the specified factor.
 * @param factor The brightness factor. It must be a double value. The default value is 0.0.
 *
 * @property factor The brightness factor. It is a read-only property of type Double.
 * @see TransformSettings
 * @see BrightnessTransformer
 * @see [link](https://sksamuel.github.io/scrimage/brightness/)
 */
open class BrightnessSettings(
    open val factor: Double = 0.0
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = BrightnessTransformer()
    }
}