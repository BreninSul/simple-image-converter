package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.TelevisionFilter

open class TelevisionFilterSettings: CommonTransformSettings {
    override fun toFilter() = TelevisionFilter()
    override fun transformerName(): String = "TelevisionFilter"
}
