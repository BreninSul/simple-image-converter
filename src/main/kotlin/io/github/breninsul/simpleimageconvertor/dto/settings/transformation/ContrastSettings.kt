package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ContrastTransformer

open class ContrastSettings(
    open val factor: Double = 0.0
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = ContrastTransformer()
    }
}