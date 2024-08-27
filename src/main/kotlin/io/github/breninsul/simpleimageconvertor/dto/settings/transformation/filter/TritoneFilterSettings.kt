package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.color.Colors
import com.sksamuel.scrimage.filter.TritoneFilter
import java.awt.Color

open class TritoneFilterSettings(
    open val shadows: Color = Colors.Transparent.awt(),
    open val midtones: Color = Colors.Transparent.awt(),
    open val highlights: Color = Colors.Transparent.awt(),
) : CommonTransformSettings {
    override fun toFilter() = TritoneFilter(shadows, midtones, highlights)
    override fun transformerName(): String = "TritoneFilter"
}