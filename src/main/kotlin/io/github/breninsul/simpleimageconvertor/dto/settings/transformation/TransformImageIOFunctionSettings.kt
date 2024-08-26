package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.ImageIOTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer

fun interface TransformImageIOFunctionSettings: TransformSettings,ImageIOTransformer {
    override fun createTransformer(): ImageTransformer = this
}