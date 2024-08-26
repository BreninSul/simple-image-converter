package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.PosterizeFilter

open class PosterizeFilterSettings(
    open val numLevels: Int = 8,
) : CommonTransformSettings {
    override fun toFilter() = PosterizeFilter(numLevels)
    override fun transformerName(): String = "PosterizeFilter"
}
