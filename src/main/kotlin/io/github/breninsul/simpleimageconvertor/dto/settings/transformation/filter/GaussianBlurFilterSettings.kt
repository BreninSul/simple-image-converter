package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.GaussianBlurFilter

open class GaussianBlurFilterSettings(
    open val radius: Int = 1,
) : CommonTransformSettings {
    override fun toFilter() = GaussianBlurFilter(radius)
    override fun transformerName(): String = "GaussianBlurFilter"
}
