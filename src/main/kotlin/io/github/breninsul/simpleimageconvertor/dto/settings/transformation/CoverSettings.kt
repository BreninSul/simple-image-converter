package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.Position
import com.sksamuel.scrimage.ScaleMethod
import io.github.breninsul.simpleimageconvertor.dto.settings.TransformSettings

open class CoverSettings(
    open val resolution: Resolution,
    open val position: Position = Position.TopLeft,
) : TransformSettings {
    override fun getOrder(): Int = -10
}