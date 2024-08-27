package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.FlipSettings

/**
 * The `FlipTransformer` class is an open class that represents
 * a transformer for flipping images. It extends the
 * `PredefinedTransformer` class and overrides the required methods.
 *
 * @constructor Creates a new `FlipTransformer` instance.
 * @see [link](https://sksamuel.github.io/scrimage/flip/)
 */
open class FlipTransformer : PredefinedTransformer<FlipSettings>(FlipSettings::class) {
    override val name: String = "Flip"

    override fun processTransformation(image: ImmutableImage, settings: FlipSettings): ImmutableImage = when (settings.type) {
        FlipSettings.Type.X -> image.flipX()
        FlipSettings.Type.Y -> image.flipY()
    }
}
