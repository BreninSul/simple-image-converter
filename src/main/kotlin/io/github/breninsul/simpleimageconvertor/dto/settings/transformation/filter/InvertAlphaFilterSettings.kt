package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.InvertAlphaFilter

open class InvertAlphaFilterSettings: CommonTransformSettings {
    override fun toFilter() = InvertAlphaFilter()
    override fun transformerName(): String = "InvertAlphaFilter"
}