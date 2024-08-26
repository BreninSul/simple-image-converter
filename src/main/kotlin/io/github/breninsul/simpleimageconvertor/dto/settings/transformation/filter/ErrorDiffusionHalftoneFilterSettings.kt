package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.ErrorDiffusionHalftoneFilter

open class ErrorDiffusionHalftoneFilterSettings(
    open val threshold: Int = 0,
) : CommonTransformSettings {
    override fun toFilter() = ErrorDiffusionHalftoneFilter(threshold)
    override fun transformerName(): String = "ErrorDiffusionHalftoneFilter"
}
