package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer

/**
 * Represents a functional interface that combines the [TransformSettings] and [ImageTransformer] interfaces.
 * It provides the implementation for the [createTransformer] function.
 */
fun interface TransformFunctionSettings: TransformSettings,ImageTransformer {
    override fun createTransformer(): ImageTransformer = this
}