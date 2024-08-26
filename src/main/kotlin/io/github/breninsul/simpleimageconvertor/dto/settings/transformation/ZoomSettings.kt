package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.ScaleMethod
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ZoomTransformer

/**
 * The `ZoomSettings` class represents the settings for zooming an image. It is a subclass of `TransformSettings`.
 *
 * @constructor Creates a `ZoomSettings` instance.
 *
 * @property scaleFactor The scale factor for zooming the image. The default value is 0.0.
 * @property scaleMethod The scale method to use for zooming the image. The default value is `ScaleMethod.Bicubic`.
 *
 * @see [link](https://sksamuel.github.io/scrimage/zoom/)
 */
open class ZoomSettings(
    open val scaleFactor: Double = 0.0,
    open val scaleMethod: ScaleMethod = ScaleMethod.Bicubic,
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = ZoomTransformer()
    }
}