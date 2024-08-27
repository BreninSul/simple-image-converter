package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.Filter
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TransformSettings
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.filter.GeneralFilterTransformer

interface CommonTransformSettings : TransformSettings {
    fun toFilter(): Filter
    fun transformerName(): String

    override fun createTransformer(): ImageTransformer {
        return GeneralFilterTransformer(transformerName(), toFilter())
    }
}