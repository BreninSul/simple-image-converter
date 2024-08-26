package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleToSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TakeSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TrimSettings

/**
 * The `TrimTransformer` class is a subclass of `PredefinedTransformer` that represents a transformer for trimming images.
 * It overrides the `processTransformation` method to perform the trimming operation on the image.
 *
 * @property name The name of the trim transformer, which is set to "Trim".
 *
 * @constructor Creates a new `TrimTransformer` object.
 *
 * @see [link](https://sksamuel.github.io/scrimage/trim/)
 */
open class TrimTransformer : PredefinedTransformer<TrimSettings>(TrimSettings::class) {
    override val name: String = "Trim"

    override fun processTransformation(image: ImmutableImage, settings: TrimSettings): ImmutableImage =
        image.trim(settings.left, settings.top, settings.right, settings.bottom)
}
