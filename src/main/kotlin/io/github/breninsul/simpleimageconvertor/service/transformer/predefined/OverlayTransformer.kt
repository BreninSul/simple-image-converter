package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.OverlaySettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException


open class OverlayTransformer : OperationWitSecondImageTransformer<OverlaySettings>(OverlaySettings::class) {
    override val name: String = "Overlay"

    override fun mapOptionsToFrame(settings: OverlaySettings, frameImage: ImmutableImage)=OverlaySettings(settings.x, settings.y, ImageOrAnimation(null, frameImage))

    override fun processTransformation(image: ImmutableImage, settings: OverlaySettings): ImmutableImage = image.overlay(settings.image.image?: throw ImageTransformerException("Image should be static"),settings.x,settings.y)
}
