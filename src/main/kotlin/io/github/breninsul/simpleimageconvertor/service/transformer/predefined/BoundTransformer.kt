package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.BoundSettings

/**
 * The `BoundTransformer` class is a concrete implementation of the `PredefinedTransformer` class. It represents a transformer that applies bound transformation on an image using
 *  the provided settings.
 *
 * @property name The name of the transformer, which is set to "Bound".
 *
 * @see [link](https://sksamuel.github.io/scrimage/bound/)
 */
open class BoundTransformer : PredefinedTransformer<BoundSettings>(BoundSettings::class) {
    override val name: String="Bound"

    override fun processTransformation(image:ImmutableImage, settings: BoundSettings): ImmutableImage = image.bound(settings.resolution.width, settings.resolution.height, settings.method)
}
