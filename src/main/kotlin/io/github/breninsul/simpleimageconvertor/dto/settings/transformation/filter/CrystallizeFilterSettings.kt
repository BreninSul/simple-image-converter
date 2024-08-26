package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.CrystallizeFilter
import java.awt.Color

open class CrystallizeFilterSettings(
    open val scale: Double = 16.0,
    open val edgeThickness: Double = 0.4,
    open val edgeColor: Color = Color.decode("0xff000000"),
    open val randomness: Double = 0.2,
    ) : CommonTransformSettings {
    override fun toFilter() = CrystallizeFilter(scale,edgeThickness,edgeColor.rgb,randomness)
    override fun transformerName(): String = "CrystallizeFilter"
}
