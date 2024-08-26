package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.dto.settings.TransformSettings
import java.awt.Color

open class PadToSettings(
    open val resolution: Resolution,
    open val colour: Color = Colors.Transparent.awt(),
) : TransformSettings {
    override fun getOrder(): Int = -10
}