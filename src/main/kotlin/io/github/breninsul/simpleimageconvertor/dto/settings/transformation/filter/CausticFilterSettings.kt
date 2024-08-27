package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.CausticsFilter
import java.awt.Color

open class CausticFilterSettings(
    open val amount: Double = 1.0,
    open val turbulence: Double = 1.0,
    open val alpha: Double = 0.2,
    open val colour: Color = Color.decode("0xff799fff")
) : CommonTransformSettings {
    override fun toFilter() = CausticsFilter(this.amount.toFloat(), this.turbulence.toFloat(), this.alpha.toFloat(), this.colour.rgb)
    override fun transformerName(): String = "CausticFilter"
}
