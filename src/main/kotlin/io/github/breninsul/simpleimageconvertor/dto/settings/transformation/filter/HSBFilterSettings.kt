package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.HSBFilter

open class HSBFilterSettings(
    open val hue: Double = 0.0,
    open val saturation: Double = 0.0,
    open val brightness: Double = 0.0,
) : CommonTransformSettings {
    override fun toFilter() = HSBFilter(hue.toFloat(), saturation.toFloat(), brightness.toFloat())
    override fun transformerName(): String = "HSBFilter"
}