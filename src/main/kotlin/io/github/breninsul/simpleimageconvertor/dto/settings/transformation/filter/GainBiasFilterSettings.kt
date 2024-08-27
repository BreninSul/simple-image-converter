package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.GainBiasFilter

open class GainBiasFilterSettings(
    open val gain: Double = 0.5,
    open val bias: Double = 0.5,
) : CommonTransformSettings {
    override fun toFilter() = GainBiasFilter(gain.toFloat(), bias.toFloat())
    override fun transformerName(): String = "GainBiasFilter"
}
