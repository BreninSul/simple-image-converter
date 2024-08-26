package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.VignetteFilter
import java.awt.Color

open class VignetteFilterSettings(
    open val start: Double = 0.85,
    open val end: Double = 0.95,
    open val blur: Double = 0.3,
    open val colour: Color = Color.BLACK,
) : CommonTransformSettings {
    override fun toFilter() = VignetteFilter(start.toFloat(), end.toFloat(), blur.toFloat(), colour)
    override fun transformerName(): String = "VignetteFilter"
}
