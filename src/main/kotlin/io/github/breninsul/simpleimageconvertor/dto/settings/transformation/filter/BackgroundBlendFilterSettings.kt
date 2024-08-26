package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.BackgroundBlendFilter

open class BackgroundBlendFilterSettings : CommonTransformSettings {
    override fun toFilter()=BackgroundBlendFilter()
    override fun transformerName(): String ="BackgroundBlend"
}
