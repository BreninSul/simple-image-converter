package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.MaximumFilter

open class MaximumFilterSettings : CommonTransformSettings {
    override fun toFilter() = MaximumFilter()
    override fun transformerName(): String = "MaximumFilter"
}
