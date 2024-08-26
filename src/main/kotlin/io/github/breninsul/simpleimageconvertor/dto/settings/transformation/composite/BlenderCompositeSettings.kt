package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.composite

import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.OperationWithImageSettings
import io.github.breninsul.simpleimageconvertor.dto.writer.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.composite.AlphaCompositeTransformer
import thirdparty.romainguy.BlendingMode

open class BlenderCompositeSettings(
    open val alpha: Double = 0.0,
    open val mode: BlendingMode = BlendingMode.ADD,
    image: ImageOrAnimation,
    animationToStaticSettings: AnimationToStaticSettings? = null,
) : OperationWithImageSettings(image, animationToStaticSettings) {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = AlphaCompositeTransformer()
    }
}