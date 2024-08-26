package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.ContrastFilter

open class ContrastFilterSettings(
    open val contrast: Double = 0.0,
) : CommonTransformSettings {
    override fun toFilter() = ContrastFilter(contrast)
    override fun transformerName(): String = "ContrastFilter"
}
