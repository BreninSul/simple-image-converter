package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.OverlaySettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException


open class OverlayTransformer : OperationWitSecondImageTransformer<OverlaySettings>(OverlaySettings::class) {
    override val name: String = "Overlay"

    override fun AnimatedGifWithDelay.processAnimation(settings: List<Settings>): AnimatedGifWithDelay {
        val srcFrames = getFramesWithDelay()
        val overlaySettings=getOperationSettings(settings)
        val newFrames = srcFrames.mapIndexed { index, imageWithDelay ->
            val destFrame=resolveDestinationFrame(srcFrames,index,imageWithDelay,overlaySettings)
            AnimatedGifWithDelay.ImageWithDelay(processTransformation(imageWithDelay.toImmutableImage(), OverlaySettings(overlaySettings.x, overlaySettings.y, ImageOrAnimation(null, destFrame))).awt(),imageWithDelay.delay,imageWithDelay.disposeMethod)
        }
        return this.withFrames(newFrames)
    }

    override fun mapOptionsToFrame(settings: OverlaySettings, frameImage: ImmutableImage)=OverlaySettings(settings.x, settings.y, ImageOrAnimation(null, frameImage))

    override fun processTransformation(image: ImmutableImage, settings: OverlaySettings): ImmutableImage = image.overlay(settings.image.image?: throw ImageTransformerException("Image should be static"),settings.x,settings.y)
}
