package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.MinimumFilter

open class MinimumFilterSettings : CommonTransformSettings {
    override fun toFilter() = MinimumFilter()
    override fun transformerName(): String = "MinimumFilter"
}
