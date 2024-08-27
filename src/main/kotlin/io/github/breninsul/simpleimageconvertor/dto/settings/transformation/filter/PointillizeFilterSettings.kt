package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.PointillizeFilter
import com.sksamuel.scrimage.filter.PointillizeGridType
import java.awt.Color

open class PointillizeFilterSettings(
    open val angle: Double = 0.0,
    open val scale: Int = 6,
    open val edgeThickness: Double = 0.4,
    open val edgeColor: Color = Color.decode("0xff000000"),
    open val fadeEdges: Boolean = false,
    open val fuzziness: Double = 0.1,
    open val gridType: PointillizeGridType = PointillizeGridType.Random,

    ) : CommonTransformSettings {
    override fun toFilter() = PointillizeFilter(angle.toFloat(), scale, edgeThickness.toFloat(), edgeColor.rgb, fadeEdges, fuzziness.toFloat(), gridType)
    override fun transformerName(): String = "PointillizeFilter"
}
