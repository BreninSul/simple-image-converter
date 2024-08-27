package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.ShearFilter

open class ShearFilterSettings(
    open val xAngle: Double = 0.0,
    open val yAngle: Double = 0.0,
) : CommonTransformSettings {
    override fun toFilter() = ShearFilter(xAngle.toFloat(), yAngle.toFloat())
    override fun transformerName(): String = "ShearFilter"
}
