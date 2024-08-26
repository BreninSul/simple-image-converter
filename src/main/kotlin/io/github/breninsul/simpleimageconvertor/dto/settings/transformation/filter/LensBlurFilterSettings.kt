package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.LensBlurFilter

open class LensBlurFilterSettings(
    open val radius: Double = 5.0,
    open val bloom: Double = 2.0,
    open val bloomThreshold: Double = 255.0,
    open val sides: Int = 5,
) : CommonTransformSettings {
    override fun toFilter() = LensBlurFilter(radius.toFloat(), bloom.toFloat(), bloomThreshold.toFloat(), sides)
    override fun transformerName(): String = "LensBlurFilter"
}