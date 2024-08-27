package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.LensFlareFilter

open class LensFlareFilterSettings : CommonTransformSettings {
    override fun toFilter() = LensFlareFilter()
    override fun transformerName(): String = "LensFlareFilter"
}
