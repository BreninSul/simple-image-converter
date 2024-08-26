package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.AutocropTransformer
import java.awt.Color

open class AutocropSettings(
    open val backgroundColour: Color = Colors.Transparent.awt(),
    open val colorTolerance: Int = 0,
    ) : TransformSettings {
    override fun createTransformer()= transformer
    companion object{
        protected val transformer= AutocropTransformer()
    }
}