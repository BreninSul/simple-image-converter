package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.EdgeAction
import com.sksamuel.scrimage.filter.UnsharpFilter

open class UnsharpFilterSettings(
    open val amount: Double = 0.5,
    open val threshold: Int = 1,
    open val edgeAction: EdgeAction = EdgeAction.ZeroEdges
) : CommonTransformSettings {
    override fun toFilter() = UnsharpFilter(amount.toFloat(), threshold, edgeAction)
    override fun transformerName(): String = "UnsharpFilter"
}
