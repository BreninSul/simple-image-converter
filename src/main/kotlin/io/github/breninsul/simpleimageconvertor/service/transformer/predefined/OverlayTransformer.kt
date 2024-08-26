package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.OverlaySettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException

/**
 * The `OverlayTransformer` class is a subclass of the abstract class `OperationWitSecondImageTransformer`.
 * It represents a transformer that overlays a second image onto the first image using the provided settings.
 *
 * @constructor Creates a new instance of `OverlayTransformer`.
 *
 * @property name The name of the transformer.
 *
 * @param T The type of the overlay settings. It must be a subclass of `OverlaySettings`.
 *
 * @see OperationWitSecondImageTransformer
 * @see OverlaySettings
 *
 * @see [link](https://sksamuel.github.io/scrimage/overlay/)
 */
open class OverlayTransformer : OperationWitSecondImageTransformer<OverlaySettings>(OverlaySettings::class) {
    override val name: String = "Overlay"

    override fun mapOptionsToFrame(settings: OverlaySettings, frameImage: ImmutableImage)=OverlaySettings(settings.x, settings.y, ImageOrAnimation(null, frameImage))

    override fun processTransformation(image: ImmutableImage, settings: OverlaySettings): ImmutableImage = image.overlay(settings.image.image?: throw ImageTransformerException("Image should be static"),settings.x,settings.y)
}
