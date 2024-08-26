package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.TakeTransformer

/**
 * The `TakeSettings` class is a subclass of `TransformSettings` interface that represents the settings for the "Take" transformer.
 *
 * @property value The value that represents the amount or size of the transformation. It is an integer value and is set to 0 by default.
 * @property type The type of transformation to be applied. It is an enum value of `Type` and is set to `Type.LEFT` by default.
 *
 * @see [link](https://sksamuel.github.io/scrimage/take/)
 */
open class TakeSettings(
    open val value: Int = 0,
    open val type: Type = Type.LEFT,
) : TransformSettings {
    enum class Type {
        LEFT, RIGHT, TOP, BOTTOM
    }

    override fun createTransformer() = transformer

    companion object {
        protected val transformer = TakeTransformer()
    }
}