package io.github.breninsul.simpleimageconvertor.service.transformer.predefined.filter

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.filter.AlphaMaskFilter
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter.AlphaMaskFilterSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.OperationWitSecondImageTransformer

/**
 * The `AlphaMaskFilterTransformer` class is an implementation of the
 * `OperationWitSecondImageTransformer` class. It represents a transformer
 * that performs the alpha mask filter operation on an image using a second
 * image as the mask.
 *
 * @param T The type of the operation settings. It must extend the
 *    `AlphaMaskFilterSettings` class.
 * @constructor Creates an `AlphaMaskFilterTransformer` instance.
 * @property name The name of the transformer which is "AlphaMaskFilter".
 * @see [link](https://sksamuel.github.io/scrimage/filters/)
 */
open class AlphaMaskFilterTransformer : OperationWitSecondImageTransformer<AlphaMaskFilterSettings>(AlphaMaskFilterSettings::class) {
    override val name: String = "AlphaMaskFilter"
    override fun mapOptionsToFrame(settings: AlphaMaskFilterSettings, frameImage: ImmutableImage): AlphaMaskFilterSettings {
        return AlphaMaskFilterSettings(settings.channel, ImageOrAnimation(null, frameImage))
    }

    override fun processTransformation(image: ImmutableImage, settings: AlphaMaskFilterSettings): ImmutableImage = image.filter(AlphaMaskFilter(settings.image.image ?: throw ImageTransformerException("Image should be static"), settings.channel))
}
