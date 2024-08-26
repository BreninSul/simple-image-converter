package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.TransformSettings

abstract class OperationWithImageSettings(
    open val image: ImageOrAnimation,
    open val animationToStaticSettings: AnimationToStaticSettings? = null,
) : TransformSettings