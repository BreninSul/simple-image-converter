package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.PadSettings

/**
 * The `PadTransformer` class is an implementation of the `PredefinedTransformer`
 * abstract class. It represents a transformer that adds padding to an image using
 * the specified settings.
 *
 * @constructor Creates a `PadTransformer` object.
 *
 * @see [link](https://sksamuel.github.io/scrimage/pad/)
 */
open class PadTransformer : PredefinedTransformer<PadSettings>(PadSettings::class) {
    override val name: String = "Pad"

    override fun processTransformation(image: ImmutableImage, settings: PadSettings): ImmutableImage = image.padWith(settings.left,settings.top,settings.right,settings.bottom)
}
