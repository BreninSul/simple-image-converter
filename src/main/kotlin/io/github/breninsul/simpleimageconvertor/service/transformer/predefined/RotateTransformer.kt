package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ResizeSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ResizeToSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.RotateSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleToSettings

/**
 * The `RotateTransformer` class is an open class that extends the `PredefinedTransformer` class.
 * It represents a transformer for rotating images using predefined settings.
 *
 * @property name The name of the transformer as a string.
 * @constructor Creates an instance of the `RotateTransformer` class.
 *
 * @see [link](https://sksamuel.github.io/scrimage/rotate/)
 *
 */
open class RotateTransformer : PredefinedTransformer<RotateSettings>(RotateSettings::class) {
    override val name: String = "Rotate"

    override fun processTransformation(image: ImmutableImage, settings: RotateSettings): ImmutableImage = image.rotate(settings.getRadians(),settings.colour)
}
