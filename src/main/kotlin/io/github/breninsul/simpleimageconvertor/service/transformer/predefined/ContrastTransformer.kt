package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ContrastSettings

/**
 * The `ContrastTransformer` class is a subclass of `PredefinedTransformer`
 * that represents a transformer for applying contrast adjustments
 * to images. It provides an implementation for processing
 * image transformations using the provided contrast settings.
 *
 * @property name The name of the transformer, which is set to "Contrast".
 *
 *    @see [link](https://sksamuel.github.io/scrimage/contrast/)
 */
open class ContrastTransformer : PredefinedTransformer<ContrastSettings>(ContrastSettings::class) {
    override val name: String = "Contrast"

    override fun processTransformation(image: ImmutableImage, settings: ContrastSettings): ImmutableImage = image.contrast(settings.factor)
}
