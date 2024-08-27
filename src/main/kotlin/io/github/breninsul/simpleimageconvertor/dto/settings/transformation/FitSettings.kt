package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.Position
import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.FitTransformer
import java.awt.Color

/**
 * The `FitSettings` class is an open class that represents the settings
 * for fitting an image into a specified resolution.
 *
 * @property resolution The resolution to fit the image into. It is an
 *    instance of [Resolution].
 * @property colour The color to use for the background if the image does
 *    not cover the entire resolution. It is an instance of [Color].
 * @property scaleMethod The scaling method to use for the image. It is an
 *    instance of [ScaleMethod].
 * @property position The position to place the image within the resolution
 *    if the aspect ratios are different. It is an instance of [Position].
 * @see [link](https://sksamuel.github.io/scrimage/fit/)
 */
open class FitSettings(
    open val resolution: Resolution,
    open val colour: Color = Colors.Transparent.toAWT(),
    open val scaleMethod: ScaleMethod = ScaleMethod.Bicubic,
    open val position: Position = Position.Center,
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = FitTransformer()
    }
}
