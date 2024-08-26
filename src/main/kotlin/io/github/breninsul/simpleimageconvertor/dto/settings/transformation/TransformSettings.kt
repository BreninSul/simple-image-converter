package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer

interface TransformSettings: Settings {
    fun createTransformer(): ImageTransformer
}