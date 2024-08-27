package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.VintageFilter

open class VintageFilterSettings : CommonTransformSettings {
    override fun toFilter() = VintageFilter()
    override fun transformerName(): String = "VintageFilter"
}
