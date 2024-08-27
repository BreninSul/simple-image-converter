package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.FitSettings

/**
 * The `FitTransformer` class is a subclass of `PredefinedTransformer`
 * that represents a transformer for fitting an image
 * into a specified resolution with additional settings.
 *
 * @param resolution The resolution to fit the image into. It is an
 *    instance of `FitSettings.Resolution`.
 * @param colour The color to use for the background if the image does not
 *    cover the entire resolution.
 * @param scaleMethod The scaling method to use for the image.
 * @param position The position to place the image within the resolution if
 *    the aspect ratios are different.
 * @see [link](https://sksamuel.github.io/scrimage/fit/)
 */
open class FitTransformer : PredefinedTransformer<FitSettings>(FitSettings::class) {
    override val name: String = "Fit"

    override fun processTransformation(image: ImmutableImage, settings: FitSettings): ImmutableImage = image.fit(settings.resolution.width, settings.resolution.height, settings.colour, settings.scaleMethod, settings.position)
}
