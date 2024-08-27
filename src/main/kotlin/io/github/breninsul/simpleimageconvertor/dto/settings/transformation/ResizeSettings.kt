package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.Position
import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ResizeTransformer
import java.awt.Color

/**
 * The `ResizeSettings` class represents the settings for resizing an
 * image.
 *
 * @param scaleFactor The scale factor used to resize the image. Default
 *    value is 0.0.
 * @param position The position of the resized image.
 * @param colour The color of the resized image. Default value is
 *    transparent color.
 * @constructor Creates a new instance of `ResizeSettings`.
 * @see [link](https://sksamuel.github.io/scrimage/resize/)
 */
open class ResizeSettings(
    open val scaleFactor: Double = 0.0,
    open val position: Position,
    open val colour: Color = Colors.Transparent.toAWT(),
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = ResizeTransformer()
    }
}