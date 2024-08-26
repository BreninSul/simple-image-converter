package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.Position
import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.FlipTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ResizeToTransformer
import java.awt.Color

open class ResizeToSettings(
    open val resolution: Resolution,
    open val position: Position,
    open val colour: Color = Colors.Transparent.toAWT(),
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = ResizeToTransformer()
    }}