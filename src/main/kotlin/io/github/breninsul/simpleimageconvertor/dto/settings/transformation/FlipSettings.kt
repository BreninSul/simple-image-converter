package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.FlipTransformer

/**
 * The `FlipSettings` class is an open class that represents the settings
 * for flipping images. It implements the `TransformSettings` interface.
 *
 * @constructor Creates a new `FlipSettings` instance with the specified
 *    type.
 * @property type The type of flip transformation. Defaults to `Type.X`.
 * @see [link](https://sksamuel.github.io/scrimage/flip/)
 */
open class FlipSettings(
    open val type: Type = Type.X,
) : TransformSettings {
    enum class Type {
        X, Y
    }

    override fun createTransformer() = transformer

    companion object {
        protected val transformer = FlipTransformer()
    }
}
