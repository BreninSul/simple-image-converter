package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.DitherFilter

open class DitherFilterSettings: CommonTransformSettings {
    override fun toFilter() = DitherFilter()
    override fun transformerName(): String = "DitherFilter"
}
