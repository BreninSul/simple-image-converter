package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.SmearFilter
import com.sksamuel.scrimage.filter.SmearType

open class SmearFilterSettings(
    open val smearType: SmearType = SmearType.Lines,
    open val angle: Double = 0.0,
    open val density: Double = 0.3,
    open val scatter: Double = 0.0,
    open val distance: Int = 3,
    open val mix: Double = 0.4,
) : CommonTransformSettings {
    override fun toFilter() = SmearFilter(smearType, angle.toFloat(), density.toFloat(), scatter.toFloat(), distance, mix.toFloat())
    override fun transformerName(): String = "SmearFilter"
}