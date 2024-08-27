package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.RobertsFilter

open class RobertsFilterSettings : CommonTransformSettings {
    override fun toFilter() = RobertsFilter()
    override fun transformerName(): String = "RobertsFilter"
}
