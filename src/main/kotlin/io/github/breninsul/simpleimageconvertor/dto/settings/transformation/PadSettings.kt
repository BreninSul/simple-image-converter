package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.PadTransformer
import java.awt.Color

/**
 * The `PadSettings` class represents the settings for adding padding to an
 * image.
 *
 * @param left The amount of padding to add to the left side of the image.
 *    Default is 0.
 * @param top The amount of padding to add to the top of the image. Default
 *    is 0.
 * @param right The amount of padding to add to the right side of the
 *    image. Default is 0.
 * @param bottom The amount of padding to add to the bottom of the image.
 *    Default is 0.
 * @param colour The color of the padding. Default is transparent.
 * @constructor Creates a `PadSettings` object with the specified
 *    parameters.
 * @see [link](https://sksamuel.github.io/scrimage/pad/)
 */
open class PadSettings(
    open val left: Int = 0,
    open val top: Int = 0,
    open val right: Int = 0,
    open val bottom: Int = 0,
    open val colour: Color = Colors.Transparent.awt(),
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = PadTransformer()
    }
}