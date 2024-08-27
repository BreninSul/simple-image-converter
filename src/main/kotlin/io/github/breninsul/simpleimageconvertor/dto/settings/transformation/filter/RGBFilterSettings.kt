package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.RGBFilter

open class RGBFilterSettings(
    open val r: Double = 0.0,
    open val g: Double = 0.0,
    open val b: Double = 0.0,
) : CommonTransformSettings {
    override fun toFilter() = RGBFilter(r.toFloat(), g.toFloat(), b.toFloat())
    override fun transformerName(): String = "RGBFilter"
}

