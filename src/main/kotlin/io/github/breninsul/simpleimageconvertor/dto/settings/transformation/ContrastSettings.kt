package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.dto.settings.TransformSettings

open class ContrastSettings(
    open val factor: Double = 0.0
) : TransformSettings {
    override fun getOrder(): Int = -10
}