package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.TranslateTransformer
import java.awt.Color

open class TranslateSettings(
    open val x: Int = 0,
    open val y: Int = 0,
    open val colour: Color = Colors.Transparent.toAWT(),

) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = TranslateTransformer()
    }
}