package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.InvertFilter

open class InvertFilterSettings: CommonTransformSettings {
    override fun toFilter() = InvertFilter()
    override fun transformerName(): String = "InvertFilter"
}
