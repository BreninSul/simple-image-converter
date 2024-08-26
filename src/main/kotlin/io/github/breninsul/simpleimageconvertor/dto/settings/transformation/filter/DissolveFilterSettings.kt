package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.DissolveFilter

open class DissolveFilterSettings(
    open val density: Double = 0.1,
) : CommonTransformSettings {
    override fun toFilter() = DissolveFilter(density.toFloat())
    override fun transformerName(): String = "DissolveFilter"
}
