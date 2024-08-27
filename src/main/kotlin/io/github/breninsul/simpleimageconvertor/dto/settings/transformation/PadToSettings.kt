package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.PadToTransformer
import java.awt.Color

/**
 * The `PadToSettings` class represents the settings for padding an image
 * to a specific resolution.
 *
 * @param resolution The resolution of the padded image. It must be an
 *    instance of the [Resolution] class.
 * @param colour The colour of the padding. It is an optional parameter and
 *    defaults to transparent.
 * @constructor Creates a `PadToSettings` object with the given resolution
 *    and colour.
 * @see [link](https://sksamuel.github.io/scrimage/pad/)
 */
open class PadToSettings(
    open val resolution: Resolution,
    open val colour: Color = Colors.Transparent.awt(),
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = PadToTransformer()
    }
}