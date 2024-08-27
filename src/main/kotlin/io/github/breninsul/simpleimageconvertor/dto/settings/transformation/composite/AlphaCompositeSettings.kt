package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.composite

import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.OperationWithImageSettings
import io.github.breninsul.simpleimageconvertor.dto.writer.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.composite.AlphaCompositeTransformer

/**
 * The `AlphaCompositeSettings` class represents the settings for applying
 * an alpha composite operation on images.
 *
 * @return An instance of the `AlphaCompositeTransformer` class.
 * @constructor Creates an instance of the `AlphaCompositeSettings` class.
 * @property alpha The level of transparency to be applied. Valid range is
 *    from 0.0 to 1.0, where 0.0 is fully transparent and 1.0 is fully
 *    opaque.
 * @property image The image or animation to be processed. It can be either
 *    an instance of `AnimatedGifWithDelay` or `ImmutableImage`.
 * @property animationToStaticSettings The settings for converting the
 *    animation to a static image. It is an instance of
 *    `AnimationToStaticSettings`, which represents the frame number and
 *    strategy for conversion.
 * @see [link](https://sksamuel.github.io/scrimage/composites/)
 */
open class AlphaCompositeSettings(
    open val alpha: Double = 0.0,
    image: ImageOrAnimation,
    animationToStaticSettings: AnimationToStaticSettings? = null,
) : OperationWithImageSettings(image, animationToStaticSettings) {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = AlphaCompositeTransformer()
    }
}