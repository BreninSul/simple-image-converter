package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.SobelsFilter

open class SobelsFilterSettings : CommonTransformSettings {
    override fun toFilter() = SobelsFilter()
    override fun transformerName(): String = "SobelsFilter"
}
