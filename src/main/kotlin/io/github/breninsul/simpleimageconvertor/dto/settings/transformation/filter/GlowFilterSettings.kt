package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.GlowFilter

open class GlowFilterSettings(
    open val amount: Double = 0.5,
) : CommonTransformSettings {
    override fun toFilter() = GlowFilter(amount.toFloat())
    override fun transformerName(): String = "GlowFilter"
}
