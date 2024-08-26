package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.OffsetFilter

open class OffsetFilterSettings(
    open val x: Int = 0,
    open val y: Int = 0,
    ) : CommonTransformSettings {
    override fun toFilter() = OffsetFilter(x,y)
    override fun transformerName(): String = "OffsetFilter"
}
