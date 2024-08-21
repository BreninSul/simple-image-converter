package io.github.breninsul.simpleimageconvertor.dto

import com.sksamuel.scrimage.ScaleMethod

open class ScaleSettings(
    open val resolution: Resolution,
    open val method: ScaleMethod = ScaleMethod.Bicubic,
) : TransformSettings {
    override fun getOrder(): Int =-10
}