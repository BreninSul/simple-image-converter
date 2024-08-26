package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.Position
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.CoverTransformer

/**
 * The `CoverSettings` class represents the settings for applying a cover effect to images.
 *
 * @param resolution The resolution of the transformed image. It must be an instance of [Resolution].
 * @param position The position of the cover effect. It defaults to [Position.TopLeft].
 *
 * @constructor Creates a new instance of `CoverSettings` with the specified resolution and position.
 *
 * @see [link](https://sksamuel.github.io/scrimage/cover/)
 */
open class CoverSettings(
    open val resolution: Resolution,
    open val position: Position = Position.TopLeft,
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = CoverTransformer()
    }
}