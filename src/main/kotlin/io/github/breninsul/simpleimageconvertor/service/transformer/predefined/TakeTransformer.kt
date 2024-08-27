package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TakeSettings

/**
 * The `TakeTransformer` class is an implementation of the
 * `PredefinedTransformer` abstract class. It represents a transformer that
 * applies cropping operations to an image based on predefined settings.
 *
 * @constructor Creates a new instance of the `TakeTransformer` class.
 * @property name The name of the transformer.
 * @see [link](https://sksamuel.github.io/scrimage/take/)
 */
open class TakeTransformer : PredefinedTransformer<TakeSettings>(TakeSettings::class) {
    override val name: String = "Take"

    override fun processTransformation(image: ImmutableImage, settings: TakeSettings): ImmutableImage =
        when (settings.type) {
            TakeSettings.Type.LEFT -> image.takeLeft(settings.value)
            TakeSettings.Type.RIGHT -> image.takeRight(settings.value)
            TakeSettings.Type.TOP -> image.takeTop(settings.value)
            TakeSettings.Type.BOTTOM -> image.takeBottom(settings.value)
        }
}
