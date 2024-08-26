package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.ImageIOTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer

/**
 * Represents a functional interface for configuring settings for transforming images using ImageIO.
 * This interface combines the [TransformSettings] and [ImageIOTransformer] interfaces.
 * Implementing classes must provide an implementation for the [createTransformer] function.
 */
fun interface TransformImageIOFunctionSettings: TransformSettings,ImageIOTransformer {
    override fun createTransformer(): ImageTransformer = this
}