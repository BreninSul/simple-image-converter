package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.color.Colors
import com.sksamuel.scrimage.filter.BorderFilter
import java.awt.Color

open class BorderFilterSettings(
    open val borderWidth: Int = 0,
    open val colour: Color = Colors.Transparent.awt()
) : CommonTransformSettings {
    override fun toFilter()= BorderFilter(borderWidth,colour)
    override fun transformerName(): String ="BorderFilter"
}
