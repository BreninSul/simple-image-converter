package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.OldPhotoFilter

open class OldPhotoFilterSettings: CommonTransformSettings {
    override fun toFilter() = OldPhotoFilter()
    override fun transformerName(): String = "OldPhotoFilter"
}
