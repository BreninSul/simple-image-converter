package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.angles.Radians
import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.RotateTransformer
import java.awt.Color

open class RotateSettings(
    open val degree: Double = 0.0,
    open val colour: Color = Colors.Transparent.toAWT(),
) : TransformSettings {
    override fun createTransformer() = transformer

    open fun getRadians(): Radians = Radians(Math.toRadians(degree))

    companion object {
        protected val transformer = RotateTransformer()
    }
}
