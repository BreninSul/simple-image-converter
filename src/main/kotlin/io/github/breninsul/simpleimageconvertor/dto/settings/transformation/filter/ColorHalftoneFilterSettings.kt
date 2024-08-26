package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.ColorHalftoneFilter

open class ColorHalftoneFilterSettings(
    open val radius: Double = 1.2,
) : CommonTransformSettings {
    override fun toFilter() = ColorHalftoneFilter(radius.toFloat())
    override fun transformerName(): String = "ColorHalftoneFilter"
}