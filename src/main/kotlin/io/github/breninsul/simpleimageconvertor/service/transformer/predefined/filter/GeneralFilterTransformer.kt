package io.github.breninsul.simpleimageconvertor.service.transformer.predefined.filter

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.filter.Filter
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter.CommonTransformSettings
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.PredefinedTransformer
import kotlin.reflect.KClass


open class GeneralFilterTransformer(
    override val name: String,
    protected open val filter: Filter,
) : PredefinedTransformer<CommonTransformSettings>(CommonTransformSettings::class) {

    override fun processTransformation(image: ImmutableImage, settings: CommonTransformSettings): ImmutableImage = image.filter(filter)
}
