package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.PixelateFilter

open class PixelateFilterSettings(
    open val blockSize: Int = 2,
) : CommonTransformSettings {
    override fun toFilter() = PixelateFilter(blockSize)
    override fun transformerName(): String = "PixelateFilter"
}
