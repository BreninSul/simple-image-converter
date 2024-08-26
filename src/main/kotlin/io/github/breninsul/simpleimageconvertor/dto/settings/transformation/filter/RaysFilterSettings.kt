package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.RaysFilter

open class RaysFilterSettings(
    open val opacity: Double = 1.0,
    open val threshold: Double = 0.0,
    open val strength: Double = 0.5,
    ) : CommonTransformSettings {
    override fun toFilter() = RaysFilter(opacity.toFloat(),threshold.toFloat(),strength.toFloat())
    override fun transformerName(): String = "RaysFilter"
}