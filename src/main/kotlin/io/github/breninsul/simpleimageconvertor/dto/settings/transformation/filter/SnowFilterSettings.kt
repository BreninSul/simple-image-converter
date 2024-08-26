package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.SnowFilter

open class SnowFilterSettings: CommonTransformSettings {
    override fun toFilter() = SnowFilter()
    override fun transformerName(): String = "SnowFilter"
}
