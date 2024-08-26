package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.ScaleMethod
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ScaleToTransformer

/**
 * The `ScaleToSettings` class represents the settings for scaling an image to a specific resolution.
 *
 * @property resolution The resolution to scale the image to. It is an instance of the `Resolution` class.
 * @property scaleMethod The method used for scaling the image. It is an instance of the `ScaleMethod` enum class and has a default value of `ScaleMethod.Bicubic`.
 *
 * @constructor Creates a new instance of `ScaleToSettings` with the specified resolution and scale method.
 *
 * @see [link](https://sksamuel.github.io/scrimage/scale/)
 */
open class ScaleToSettings(
    open val resolution: Resolution,
    open val scaleMethod: ScaleMethod = ScaleMethod.Bicubic,
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = ScaleToTransformer()
    }
}