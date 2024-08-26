package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.TwirlFilter

open class TwirlFilterSettings(
    open val angle: Double = (Math.PI / 1.5),
    open val radius: Double = 0.0,
    open val centerX: Double = 0.5,
    open val centerY: Double = 0.5,
) : CommonTransformSettings {
    override fun toFilter() = TwirlFilter(angle.toFloat(), radius.toFloat(), centerX.toFloat(), centerY.toFloat())
    override fun transformerName(): String = "TwirlFilter"
}