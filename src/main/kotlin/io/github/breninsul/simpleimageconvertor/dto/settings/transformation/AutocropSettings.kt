package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.AutocropTransformer
import java.awt.Color

/**
 * The `AutocropSettings` class represents the settings for autocropping an image.
 * It extends the `TransformSettings` interface and provides default values for the background color and color tolerance properties.
 *
 * @property backgroundColour The background color to be used for autocropping. The default value is transparent.
 * @property colourTolerance The tolerance level for color matching during autocropping. The default value is 0.
 *
 * @see TransformSettings
 * @see AutocropTransformer
 * @see [AutocropTransformer](https://sksamuel.github.io/scrimage/autocrop/)
 */
open class AutocropSettings(
    open val backgroundColour: Color = Colors.Transparent.awt(),
    open val colourTolerance: Int = 0,
    ) : TransformSettings {
    override fun createTransformer()= transformer
    companion object{
        protected val transformer= AutocropTransformer()
    }
}