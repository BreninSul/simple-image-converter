package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.writer.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.OverlayTransformer

/**
 * The `OverlaySettings` class represents the settings for overlaying
 * an image on top of another image. It is a subclass of the
 * `OperationWithImageSettings` class.
 *
 * @constructor Creates a new instance of `OverlaySettings` with the
 *    specified parameters.
 * @property x The x-coordinate of the top-left corner of the overlay image
 *    on the base image. Default value is 0.
 * @property y The y-coordinate of the top-left corner of the overlay image
 *    on the base image. Default value is 0.
 * @property image The image or animation to be overlayed onto the base
 *    image.
 * @property animationToStaticSettings The settings for converting an
 *    animation to a static image. It is an instance of the
 *    `AnimationToStaticSettings` class.
 * @see [link](https://sksamuel.github.io/scrimage/overlay/)
 */
open class OverlaySettings(
    open val x: Int = 0,
    open val y: Int = 0,
    image: ImageOrAnimation,
    animationToStaticSettings: AnimationToStaticSettings? = null,
) : OperationWithImageSettings(image, animationToStaticSettings) {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = OverlayTransformer()
    }
}