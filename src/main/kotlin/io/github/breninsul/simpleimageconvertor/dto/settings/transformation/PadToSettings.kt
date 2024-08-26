package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.FlipTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.PadToTransformer
import java.awt.Color

open class PadToSettings(
    open val resolution: Resolution,
    open val colour: Color = Colors.Transparent.awt(),
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = PadToTransformer()
    }}