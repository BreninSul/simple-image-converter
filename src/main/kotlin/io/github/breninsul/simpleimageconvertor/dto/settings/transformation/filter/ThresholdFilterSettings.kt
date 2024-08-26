package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.ThresholdFilter
import java.awt.Color

open class ThresholdFilterSettings(
    open val threshold: Int = 127,
    open val white: Color = Color.WHITE,
    open val black: Color = Color.BLACK,
    ) : CommonTransformSettings {
    override fun toFilter() = ThresholdFilter(threshold,white.rgb,black.rgb)
    override fun transformerName(): String = "ThresholdFilter"
}
