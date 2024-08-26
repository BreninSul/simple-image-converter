package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.BrightnessFilter

open class BrightnessFilterSettings(
    open val brightness: Double = 0.0,
) : CommonTransformSettings {
    override fun toFilter()= BrightnessFilter(brightness.toFloat())
    override fun transformerName(): String ="BrightnessFilter"
}
