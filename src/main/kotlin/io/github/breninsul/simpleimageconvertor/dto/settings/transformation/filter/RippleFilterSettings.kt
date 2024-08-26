package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.RippleFilter
import com.sksamuel.scrimage.filter.RippleType

open class RippleFilterSettings(
    open val rippleType: RippleType = RippleType.Sine,
    open val xAmplitude: Double = 2.0,
    open val yAmplitude: Double = 2.0,
    open val xWavelength: Double = 6.0,
    open val yWavelength: Double = 6.0,
) : CommonTransformSettings {
    override fun toFilter() = RippleFilter(rippleType, xAmplitude.toFloat(), yAmplitude.toFloat(), xWavelength.toFloat(), yWavelength.toFloat())
    override fun transformerName(): String = "RippleFilter"
}

