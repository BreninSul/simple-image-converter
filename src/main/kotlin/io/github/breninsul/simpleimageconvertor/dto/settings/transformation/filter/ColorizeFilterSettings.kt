package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.color.Colors
import com.sksamuel.scrimage.filter.ColorizeFilter
import java.awt.Color

open class ColorizeFilterSettings(
    open val colour: Color = Colors.Transparent.awt(),
) : CommonTransformSettings {
    override fun toFilter() = ColorizeFilter(colour)
    override fun transformerName(): String = "ColorizeFilter"
}