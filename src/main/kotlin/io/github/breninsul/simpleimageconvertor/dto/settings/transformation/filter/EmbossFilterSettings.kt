package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.EmbossFilter

open class EmbossFilterSettings : CommonTransformSettings {
    override fun toFilter() = EmbossFilter()
    override fun transformerName(): String = "EmbossFilter"
}
