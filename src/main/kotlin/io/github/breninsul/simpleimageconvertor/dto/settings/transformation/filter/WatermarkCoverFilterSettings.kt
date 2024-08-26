package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.FontUtils
import com.sksamuel.scrimage.filter.WatermarkCoverFilter
import java.awt.Color
import java.awt.Font

open class WatermarkCoverFilterSettings(
    open val text: String = "",
    open val font: Font = FontUtils.createFont(Font.SANS_SERIF, 12),
    open val antiAlias: Boolean = true,
    open val alpha: Double = 0.1,
    open val colour: Color = Color.WHITE,
) : CommonTransformSettings {
    override fun toFilter() = WatermarkCoverFilter(text, font, antiAlias, alpha, colour)
    override fun transformerName(): String = "WatermarkCoverFilter"
}



