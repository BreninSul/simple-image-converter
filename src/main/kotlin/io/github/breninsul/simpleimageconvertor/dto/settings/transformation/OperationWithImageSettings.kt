package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.writer.AnimationToStaticSettings

/**
 * The `OperationWithImageSettings` class is an abstract class that represents the settings for operations that involve an image or animation.
 *
 * @property image The image or animation to be processed. It can be either an instance of `AnimatedGifWithDelay` or `ImmutableImage`.
 * @property animationToStaticSettings The settings for converting the animation to a static image. It is an instance of `AnimationToStaticSettings`, which represents the frame number
 *  and strategy for conversion.
 */
abstract class OperationWithImageSettings(
    open val image: ImageOrAnimation,
    open val animationToStaticSettings: AnimationToStaticSettings? = null,
) : TransformSettings