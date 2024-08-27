package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.LaplaceFilter

open class LaplaceFilterSettings : CommonTransformSettings {
    override fun toFilter() = LaplaceFilter()
    override fun transformerName(): String = "LaplaceFilter"
}
