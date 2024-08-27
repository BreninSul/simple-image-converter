package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.SolarizeFilter

open class SolarizeFilterSettings : CommonTransformSettings {
    override fun toFilter() = SolarizeFilter()
    override fun transformerName(): String = "SolarizeFilter"
}
