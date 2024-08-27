package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.SwimFilter
import java.util.*

open class SwimFilterSettings(
    open val random: Random = Random(),
    open val amount: Double = 6.0,
    open val stretch: Double = 2.0,
) : CommonTransformSettings {
    override fun toFilter() = SwimFilter(random, amount.toFloat(), stretch.toFloat())
    override fun transformerName(): String = "SwimFilter"
}