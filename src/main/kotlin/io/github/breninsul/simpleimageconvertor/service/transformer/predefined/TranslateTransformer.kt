package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TranslateSettings

/**
 * The `TranslateTransformer` class is a concrete implementation of the
 * abstract class `PredefinedTransformer`. It represents a transformer
 * that performs translation on an image using the provided settings.
 *
 * @constructor Creates a `TranslateTransformer` instance.
 * @property name The name of the transformer.
 * @see [link](https://sksamuel.github.io/scrimage/translate/)
 */
open class TranslateTransformer : PredefinedTransformer<TranslateSettings>(TranslateSettings::class) {
    override val name: String = "Translate"

    override fun processTransformation(image: ImmutableImage, settings: TranslateSettings): ImmutableImage =
        image.translate(settings.x, settings.y, settings.colour)
}
