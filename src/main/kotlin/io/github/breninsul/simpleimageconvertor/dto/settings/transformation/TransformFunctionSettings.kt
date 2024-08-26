package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer

fun interface TransformFunctionSettings: TransformSettings,ImageTransformer {
    override fun createTransformer(): ImageTransformer = this
}