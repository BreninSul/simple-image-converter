package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.BlackThresholdFilter

open class BlackThresholdFilterSettings(open val thresholdPercentage:Double = 0.0) : CommonTransformSettings {
    override fun toFilter()= BlackThresholdFilter(thresholdPercentage)
    override fun transformerName(): String ="BlackThreshold"
}
