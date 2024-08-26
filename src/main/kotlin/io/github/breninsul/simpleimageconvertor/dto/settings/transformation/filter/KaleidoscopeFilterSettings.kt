package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.KaleidoscopeFilter

open class KaleidoscopeFilterSettings(
    open val sides: Int = 3,
) : CommonTransformSettings {
    override fun toFilter() = KaleidoscopeFilter(sides)
    override fun transformerName(): String = "KaleidoscopeFilter"
}
