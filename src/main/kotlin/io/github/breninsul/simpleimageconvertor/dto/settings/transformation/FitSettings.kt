package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.Position
import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.dto.settings.TransformSettings
import java.awt.Color

open class FitSettings(
    open val resolution: Resolution,
    open val colour: Color = Colors.Transparent.toAWT(),
    open val scaleMethod: ScaleMethod = ScaleMethod.Bicubic,
    open val position: Position = Position.Center,
) : TransformSettings {
    override fun getOrder(): Int = -10
}
