package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.BlurFilter

open class BlurFilterSettings : CommonTransformSettings {
    override fun toFilter() = BlurFilter()
    override fun transformerName(): String = "BlurFilter"
}
