package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.FillSettings

/**
 * The `FillTransformer` class is an implementation of the abstract class
 * `PredefinedTransformer`. It represents a transformer that performs a
 * fill operation on an image using the provided settings.
 *
 * @property name The name of the fill transformer. It is a string value.
 * @property clazz The class of the fill settings type. It is used to
 *    identify and fetch the appropriate settings from the list of
 *    settings.
 * @see [link](https://sksamuel.github.io/scrimage/fill/)
 */
open class FillTransformer : PredefinedTransformer<FillSettings>(FillSettings::class) {
    override val name: String = "Fill"

    override fun processTransformation(image: ImmutableImage, settings: FillSettings): ImmutableImage = image.fill(settings.getPainter())
}
