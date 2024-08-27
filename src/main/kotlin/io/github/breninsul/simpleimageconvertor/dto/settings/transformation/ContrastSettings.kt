package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ContrastTransformer

/**
 * The `ContrastSettings` class represents settings for applying contrast
 * adjustments to images. It is an open class that implements the
 * `TransformSettings` interface.
 *
 * @param factor The contrast adjustment factor. Defaults to 0.0 if not
 *    provided.
 * @constructor Creates a new instance of the `ContrastSettings` class with
 *    the specified contrast adjustment factor.
 * @property factor The contrast adjustment factor. It is a double value
 *    that ranges from 0.0 to 1.0, representing the extent of the contrast
 *    adjustment to be applied.
 * @see [link](https://sksamuel.github.io/scrimage/contrast/)
 */
open class ContrastSettings(
    open val factor: Double = 0.0
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = ContrastTransformer()
    }
}