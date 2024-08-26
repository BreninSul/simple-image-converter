package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.OpacityFilter
import com.sksamuel.scrimage.filter.SparkleFilter

open class SparkleFilterSettings(
    open val x: Int = 0,
    open val y: Int = 0,
    open val rays: Int = 50,
    open val radius: Int = 25,
    open val amount: Int = 50,
    ) : CommonTransformSettings {
    override fun toFilter() = SparkleFilter(x, y, rays, radius, amount)
    override fun transformerName(): String = "SparkleFilter"
}
