package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.NashvilleFilter

open class NashvilleFilterSettings : CommonTransformSettings {
    override fun toFilter() = NashvilleFilter()
    override fun transformerName(): String = "NashvilleFilter"
}
