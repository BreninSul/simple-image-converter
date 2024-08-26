package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.SummerFilter

open class SummerFilterSettings(
    open val vignette: Boolean = false,
) : CommonTransformSettings {
    override fun toFilter() = SummerFilter(vignette)
    override fun transformerName(): String = "SummerFilter"
}
