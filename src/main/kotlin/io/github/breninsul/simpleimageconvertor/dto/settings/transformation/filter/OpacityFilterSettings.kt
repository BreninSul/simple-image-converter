package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.OpacityFilter

open class OpacityFilterSettings(
    open val amount: Double = 0.0,
) : CommonTransformSettings {
    override fun toFilter() = OpacityFilter(amount.toFloat())
    override fun transformerName(): String = "OpacityFilter"
}
