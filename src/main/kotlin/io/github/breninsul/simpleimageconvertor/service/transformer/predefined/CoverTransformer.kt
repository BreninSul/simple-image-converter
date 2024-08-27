package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.CoverSettings

/**
 * The `CoverTransformer` class is a subclass of `PredefinedTransformer`
 * that represents a transformer for applying a cover effect to images. It
 * overrides the `processTransformation` method to implement the specific
 * cover transformation logic.
 *
 * @property name The name of the cover transformer.
 *
 *    @see [link](https://sksamuel.github.io/scrimage/cover/)
 */
open class CoverTransformer : PredefinedTransformer<CoverSettings>(CoverSettings::class) {
    override val name: String = "Cover"

    override fun processTransformation(image: ImmutableImage, settings: CoverSettings): ImmutableImage = image.cover(settings.resolution.width, settings.resolution.height, settings.position)
}
