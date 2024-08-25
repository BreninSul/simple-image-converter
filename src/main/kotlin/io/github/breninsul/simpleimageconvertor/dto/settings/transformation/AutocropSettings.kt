package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.dto.settings.TransformSettings
import java.awt.Color

open class AutocropSettings(
    open val backgroundColour: Color = Colors.Transparent.awt(),
    open val colorTolerance: Int = 0,
    ) : TransformSettings {
        override fun getOrder(): Int =-10
    }