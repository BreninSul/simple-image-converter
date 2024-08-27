package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.SepiaFilter

open class SepiaFilterSettings : CommonTransformSettings {
    override fun toFilter() = SepiaFilter()
    override fun transformerName(): String = "SepiaFilter"
}
