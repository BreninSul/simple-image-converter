package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.FontUtils
import com.sksamuel.scrimage.color.Colors
import com.sksamuel.scrimage.filter.CaptionFilter
import com.sksamuel.scrimage.filter.Padding
import java.awt.Color
import java.awt.Font

open class CaptionFilterSettings(
    open val font: Font = FontUtils.createFont(Font.SANS_SERIF, 12),
    open val text: String = "",
    open val x: Int = 0,
    open val y: Int = 0,
    open val textColor: Color? = Color.BLACK,
    open val textAlpha: Double = 0.0,
    open val antiAlias: Boolean = false,
    open val fullWidth: Boolean = false,
    open val captionBackground: Color = Colors.Transparent.awt(),
    open val captionAlpha: Double = 0.0,
    open val padding: Padding = Padding(0, 0, 0, 0)
) : CommonTransformSettings {
    override fun toFilter()= CaptionFilter(this.text,this.x,this.y,this.font,this.textColor,this.textAlpha,this.antiAlias,this.fullWidth,this.captionBackground,this.captionAlpha,this.padding)
    override fun transformerName(): String ="CaptionFilter"
}

