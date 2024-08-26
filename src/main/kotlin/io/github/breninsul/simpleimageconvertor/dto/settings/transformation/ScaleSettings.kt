package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.ScaleMethod
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ScaleTransformer

/**
 * The `ScaleSettings` class represents the settings for scaling images. It implements the `TransformSettings` interface.
 *
 * @property scaleFactor The scale factor to apply to the image. It is a decimal value.
 * @property scaleMethod The method used for scaling the image. It is a value from the `ScaleMethod` enum.
 * @constructor Creates a `ScaleSettings` instance with the specified scale factor and scale method.
 *
 * @see [link](https://sksamuel.github.io/scrimage/scale/)
 */
open class ScaleSettings(
    open val scaleFactor: Double = 0.0,
    open val scaleMethod: ScaleMethod = ScaleMethod.Bicubic,
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = ScaleTransformer()
    }
}