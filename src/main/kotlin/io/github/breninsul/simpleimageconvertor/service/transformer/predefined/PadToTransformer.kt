package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.PadToSettings

/**
 * The `PadToTransformer` class is an implementation of the
 * `PadToTransformer` abstract class. It represents a transformer
 * that adds padding to an image using the specified settings.
 *
 * @constructor Creates a `PadTransformer` object.
 * @see [link](https://sksamuel.github.io/scrimage/pad/)
 */
open class PadToTransformer : PredefinedTransformer<PadToSettings>(PadToSettings::class) {
    override val name: String = "PadTo"

    override fun processTransformation(image: ImmutableImage, settings: PadToSettings): ImmutableImage = image.padTo(settings.resolution.width, settings.resolution.height, settings.colour)
}
