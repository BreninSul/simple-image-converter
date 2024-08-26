package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.TakeTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.TrimTransformer

open class TrimSettings(
    open val left: Int = 0,
    open val right: Int = 0,
    open val top: Int = 0,
    open val bottom: Int = 0,
) : TransformSettings {

    override fun createTransformer() = transformer

    companion object {
        protected val transformer = TrimTransformer()
    }
}