package io.github.breninsul.simpleimageconvertor.service.transformer.predefined.filter

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.filter.Filter
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter.CommonTransformSettings
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.PredefinedTransformer
import kotlin.reflect.KClass

/**
 * The `GeneralFilterTransformer` class is an open class that represents a transformer for applying a filter to an image.
 * It extends the abstract class `PredefinedTransformer` and implements the `ImageTransformer` interface.
 *
 * @param name The name of the transformer.
 * @param filter The filter to apply to the image.
 *
 * @see [link](https://sksamuel.github.io/scrimage/filters/)
 */
open class GeneralFilterTransformer(
    override val name: String,
    protected open val filter: Filter,
) : PredefinedTransformer<CommonTransformSettings>(CommonTransformSettings::class) {

    override fun processTransformation(image: ImmutableImage, settings: CommonTransformSettings): ImmutableImage = image.filter(filter)
}
