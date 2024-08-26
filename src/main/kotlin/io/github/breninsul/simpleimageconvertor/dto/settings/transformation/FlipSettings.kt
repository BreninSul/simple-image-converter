package io.github.breninsul.simpleimageconvertor.dto.settings.transformation


import io.github.breninsul.simpleimageconvertor.dto.settings.TransformSettings

open class FlipSettings(
    open val type: Type=Type.X,
) : TransformSettings {
    enum class Type{
        X,Y
    }
    override fun getOrder(): Int = -10
}
