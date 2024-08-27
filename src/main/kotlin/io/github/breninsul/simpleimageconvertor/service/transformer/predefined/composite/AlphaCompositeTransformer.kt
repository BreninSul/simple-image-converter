package io.github.breninsul.simpleimageconvertor.service.transformer.predefined.composite

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.composite.AlphaComposite
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.composite.AlphaCompositeSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.OperationWitSecondImageTransformer

/**
 * This class represents a transformer that applies the alpha composite
 * operation on images. It extends the `OperationWitSecondImageTransformer`
 * class and provides implementations for mapping options to frames and
 * performing the transformation.
 *
 * @constructor Creates an instance of the `AlphaCompositeTransformer`
 *    class.
 * @property name The name of the transformer, which is "AlphaComposite".
 * @see [link](https://sksamuel.github.io/scrimage/transforms/)
 */
open class AlphaCompositeTransformer : OperationWitSecondImageTransformer<AlphaCompositeSettings>(AlphaCompositeSettings::class) {
    override val name: String = "AlphaComposite"

    override fun mapOptionsToFrame(settings: AlphaCompositeSettings, frameImage: ImmutableImage): AlphaCompositeSettings {
        return AlphaCompositeSettings(settings.alpha, ImageOrAnimation(null, frameImage))
    }

    override fun processTransformation(image: ImmutableImage, settings: AlphaCompositeSettings): ImmutableImage = image.composite(AlphaComposite(settings.alpha), settings.image.image ?: throw ImageTransformerException("Image should be static"))
}
