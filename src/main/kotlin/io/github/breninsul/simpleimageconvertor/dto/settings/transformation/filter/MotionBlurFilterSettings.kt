package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.MotionBlurFilter

open class MotionBlurFilterSettings(
    open val angle: Double = 0.0,
    open val distance: Double = 0.0,
    open val rotation: Double = 0.0,
    open val zoom: Double = 0.0,
) : CommonTransformSettings {
    override fun toFilter() = MotionBlurFilter(angle, distance, rotation, zoom)
    override fun transformerName(): String = "MotionBlurFilter"
}
