package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.RylandersFilter

open class RylandersFilterSettings : CommonTransformSettings {
    override fun toFilter() = RylandersFilter()
    override fun transformerName(): String = "RylandersFilter"
}
