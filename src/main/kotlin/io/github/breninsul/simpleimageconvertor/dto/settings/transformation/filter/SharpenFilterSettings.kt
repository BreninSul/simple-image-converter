package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.SharpenFilter

open class SharpenFilterSettings : CommonTransformSettings {
    override fun toFilter() = SharpenFilter()
    override fun transformerName(): String = "SharpenFilter"
}
