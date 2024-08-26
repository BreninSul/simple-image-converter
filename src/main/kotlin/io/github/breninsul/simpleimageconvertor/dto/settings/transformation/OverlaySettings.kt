package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.AnimationToStaticSettings

open class OverlaySettings(
    open val x: Int=0,
    open val y: Int=0,
     image:ImageOrAnimation,
     animationToStaticSettings: AnimationToStaticSettings? = null,
) : OperationWithImageSettings(image,animationToStaticSettings) {
    override fun getOrder(): Int = -10
}