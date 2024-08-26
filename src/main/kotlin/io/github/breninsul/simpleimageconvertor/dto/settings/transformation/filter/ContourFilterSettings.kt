package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.ContourFilter

open class ContourFilterSettings(
    open val levels: Int = 3,
) : CommonTransformSettings {
    override fun toFilter() = ContourFilter(levels)
    override fun transformerName(): String = "ContourFilter"
}
