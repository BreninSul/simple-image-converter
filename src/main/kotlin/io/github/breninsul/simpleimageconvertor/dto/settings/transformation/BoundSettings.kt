package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.ScaleMethod
import io.github.breninsul.simpleimageconvertor.dto.settings.TransformSettings

open class BoundSettings(
    open val resolution: Resolution,
    open val method: ScaleMethod = ScaleMethod.Bicubic,
    ) : TransformSettings {
        override fun getOrder(): Int =-10
    }
