package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.dto.settings.TransformSettings
import java.awt.Color

open class PadSettings(
    open val left: Int=0,
    open val top: Int=0,
    open val right: Int=0,
    open val bottom: Int=0,
    open val colour: Color = Colors.Transparent.awt(),
) : TransformSettings {
    override fun getOrder(): Int = -10
}