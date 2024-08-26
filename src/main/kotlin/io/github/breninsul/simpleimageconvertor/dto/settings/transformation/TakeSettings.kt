package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.TakeTransformer

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