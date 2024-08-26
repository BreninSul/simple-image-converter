package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.PrewittFilter

open class PrewittFilterSettings: CommonTransformSettings {
    override fun toFilter() = PrewittFilter()
    override fun transformerName(): String = "PrewittFilter"
}
