package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.ScaleMethod
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.BoundTransformer

/**
 * The `BoundSettings` class represents a set of settings for performing
 * bound transformation on an image. It extends the `TransformSettings`
 * interface.
 *
 * @param resolution The resolution of the transformed image.
 * @param method The scale method to be used for the bound transformation.
 * @constructor Creates a `BoundSettings` instance with the specified
 *    resolution and scale method.
 * @property resolution The resolution of the transformed image.
 * @property method The scale method to be used for the bound
 *    transformation. It has a default value of `ScaleMethod.Bicubic`.
 * @see BoundSettings (https://sksamuel.github.io/scrimage/bound/)
 */
open class BoundSettings(
    open val resolution: Resolution,
    open val method: ScaleMethod = ScaleMethod.Bicubic,
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = BoundTransformer()
    }
}
