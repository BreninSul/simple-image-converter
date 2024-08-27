package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.EdgeFilter

open class EdgeFilterSettings : CommonTransformSettings {
    override fun toFilter() = EdgeFilter()
    override fun transformerName(): String = "EdgeFilter"
}
