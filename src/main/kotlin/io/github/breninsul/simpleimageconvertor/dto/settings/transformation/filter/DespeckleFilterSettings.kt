package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.DespeckleFilter

open class DespeckleFilterSettings : CommonTransformSettings {
    override fun toFilter() = DespeckleFilter()
    override fun transformerName(): String = "DespeckleFilter"
}
