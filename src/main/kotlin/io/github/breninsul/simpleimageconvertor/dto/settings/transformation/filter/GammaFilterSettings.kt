package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.GammaFilter

open class GammaFilterSettings(
    open val gamma: Double = 1.0,
) : CommonTransformSettings {
    override fun toFilter() = GammaFilter(gamma)
    override fun transformerName(): String = "GammaFilter"
}
