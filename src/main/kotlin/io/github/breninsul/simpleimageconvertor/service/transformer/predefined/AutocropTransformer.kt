package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.AutocropSettings

/**
 * The `AutocropTransformer` class is a subclass of the `PredefinedTransformer` class that represents a transformer
 * for autocropping images. It provides functionality to autocrop an image based on the specified settings.
 *
 * @constructor Creates an instance of the `AutocropTransformer` class.
 *
 * @property name The name of the transformer, which is set to "Autocrop".
 *
 * @see [link](https://sksamuel.github.io/scrimage/autocrop/)
 */
open class AutocropTransformer : PredefinedTransformer<AutocropSettings>(AutocropSettings::class)  {
    override val name: String="Autocrop"

    override fun processTransformation(image: ImmutableImage, settings: AutocropSettings):  ImmutableImage = image.autocrop(settings.backgroundColour,settings.colorTolerance)
}
