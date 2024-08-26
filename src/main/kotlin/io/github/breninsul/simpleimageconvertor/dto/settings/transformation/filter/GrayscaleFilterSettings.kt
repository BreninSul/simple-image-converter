package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.GrayscaleFilter

open class GrayscaleFilterSettings: CommonTransformSettings {
    override fun toFilter() = GrayscaleFilter()
    override fun transformerName(): String = "GrayscaleFilter"
}
