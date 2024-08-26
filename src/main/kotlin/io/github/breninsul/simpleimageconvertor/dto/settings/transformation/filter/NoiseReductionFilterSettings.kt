package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.NoiseReductionFilter

open class NoiseReductionFilterSettings: CommonTransformSettings {
    override fun toFilter() = NoiseReductionFilter()
    override fun transformerName(): String = "NoiseReductionFilter"
}
