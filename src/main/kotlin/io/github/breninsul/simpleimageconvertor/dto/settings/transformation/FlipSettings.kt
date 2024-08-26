package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.FlipTransformer


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
