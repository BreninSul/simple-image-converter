package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.GothamFilter

open class GothamFilterSettings : CommonTransformSettings {
    override fun toFilter() = GothamFilter()
    override fun transformerName(): String = "GothamFilter"
}
