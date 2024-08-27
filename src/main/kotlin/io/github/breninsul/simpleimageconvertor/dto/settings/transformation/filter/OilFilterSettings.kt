package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.OilFilter

open class OilFilterSettings(
    open val range: Int = 3,
    open val levels: Int = 256,
) : CommonTransformSettings {
    override fun toFilter() = OilFilter(range, levels)
    override fun transformerName(): String = "OilFilter"
}
