package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.NoiseFilter

open class NoiseFilterSettings(
    open val amount: Int = 25,
    open val density: Double = 1.0,
    ) : CommonTransformSettings {
    override fun toFilter() = NoiseFilter(amount, density)
    override fun transformerName(): String = "NoiseFilter"
}