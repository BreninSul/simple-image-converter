package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.canvas.painters.*
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.FillTransformer
import java.awt.Color

/**
 * The `FillSettings` class represents the settings for performing a
 * fill operation on an image. It inherits from the `TransformSettings`
 * interface and provides methods to retrieve the painter for the fill
 * operation.
 *
 * @property colour The primary color to be used for the fill operation. It
 *    is an optional value.
 * @property colourPainter The painter for the fill operation that uses a
 *    solid color. It is an optional value.
 * @property randomPainter The painter for the fill operation that uses a
 *    random color. It is an optional value.
 * @property linearGradient The linear gradient for the fill operation. It
 *    is an optional value.
 * @property radialGradient The radial gradient for the fill operation. It
 *    is an optional value.
 * @see [link](https://sksamuel.github.io/scrimage/fill/)
 */
open class FillSettings(
    open val colour: Color?,
    open val colourPainter: ColorPainter?,
    open val randomPainter: RandomPainter?,
    open val linearGradient: LinearGradient?,
    open val radialGradient: RadialGradient?,
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = FillTransformer()
    }

    open fun getPainter(): Painter {
        return if (colour != null) ColorPainter(colour) else (((colourPainter ?: randomPainter) ?: linearGradient) ?: radialGradient) ?: throw ImageTransformerException("No painter/colour set")
    }
}