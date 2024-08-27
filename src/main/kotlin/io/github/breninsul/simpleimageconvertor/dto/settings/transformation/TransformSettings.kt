package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer

/**
 * The `TransformSettings` interface extends the `Settings` interface and
 * represents a set of settings for image transformation. It provides
 * a single function `createTransformer()` that returns an instance of
 * `ImageTransformer`.
 *
 * @see Settings
 * @see ImageTransformer
 */
interface TransformSettings : Settings {
    fun createTransformer(): ImageTransformer
}