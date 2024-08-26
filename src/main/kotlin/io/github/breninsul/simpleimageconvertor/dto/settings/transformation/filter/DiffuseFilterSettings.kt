package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.DiffuseFilter

open class DiffuseFilterSettings(
    open val scale: Double = 4.0,
) : CommonTransformSettings {
    override fun toFilter() = DiffuseFilter(scale.toFloat())
    override fun transformerName(): String = "DiffuseFilter"
}
