package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.QuantizeFilter

open class QuantizeFilterSettings(
    open val colours: Int = 256,
    open val dither: Boolean = false,
) : CommonTransformSettings {
    override fun toFilter() = QuantizeFilter(colours, dither)
    override fun transformerName(): String = "QuantizeFilter"
}
