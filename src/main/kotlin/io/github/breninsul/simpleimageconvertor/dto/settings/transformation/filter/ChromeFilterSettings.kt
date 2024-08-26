package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.ChromeFilter

open class ChromeFilterSettings(
    open val amount: Double = 0.5,
    open val exposure: Double = 1.0,
) : CommonTransformSettings {
    override fun toFilter() = ChromeFilter(this.amount.toFloat(), this.exposure.toFloat())
    override fun transformerName(): String = "ChromeFilter"
}
