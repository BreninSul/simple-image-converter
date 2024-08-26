package io.github.breninsul.simpleimageconvertor.service.transformer.predefined.filter

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.filter.ErrorSpotterFilter
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter.ErrorSpotterFilterSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.OperationWitSecondImageTransformer

/**
 * This class represents an implementation of the OperationWitSecondImageTransformer abstract class.
 * It performs image processing operations using the ErrorSpotterFilter.
 * It provides methods to map options to a frame, process the transformation, and resolve destination frames.
 *
 * @constructor Creates an ErrorSpotterFilterTransformer instance.
 */
open class ErrorSpotterFilterTransformer: OperationWitSecondImageTransformer<ErrorSpotterFilterSettings>(ErrorSpotterFilterSettings::class)  {
    override val name: String="ErrorSpotterFilter"
    override fun mapOptionsToFrame(settings: ErrorSpotterFilterSettings, frameImage: ImmutableImage): ErrorSpotterFilterSettings {
        return  ErrorSpotterFilterSettings(settings.ratio, ImageOrAnimation(null, frameImage))
    }

    override fun processTransformation(image: ImmutableImage, settings: ErrorSpotterFilterSettings): ImmutableImage = image.filter(ErrorSpotterFilter(settings.image.image?: throw ImageTransformerException("Image should be static"),settings.ratio))
}
