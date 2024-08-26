package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleToSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ZoomSettings

/**
 * The `ZoomTransformer` class is a subclass of `PredefinedTransformer` that represents a transformer for zooming images using the provided `ZoomSettings`.
 *
 * @constructor Creates a `ZoomTransformer` instance.
 *
 * @property name The name of the zoom transformer.
 *
 * @see PredefinedTransformer
 * @see ZoomSettings
 */
open class ZoomTransformer : PredefinedTransformer<ZoomSettings>(ZoomSettings::class) {
    override val name: String = "Zoom"

    override fun processTransformation(image: ImmutableImage, settings: ZoomSettings): ImmutableImage = image.zoom(settings.scaleFactor, settings.scaleMethod)
}
