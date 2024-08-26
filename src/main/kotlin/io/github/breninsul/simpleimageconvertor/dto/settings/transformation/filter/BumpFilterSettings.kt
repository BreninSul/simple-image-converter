package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.BumpFilter

open class BumpFilterSettings: CommonTransformSettings {
    override fun toFilter()= BumpFilter()
    override fun transformerName(): String ="BumpFilter"
}
