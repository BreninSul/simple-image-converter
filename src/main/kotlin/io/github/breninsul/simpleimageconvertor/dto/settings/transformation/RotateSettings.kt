package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.angles.Radians
import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.RotateTransformer
import java.awt.Color

/**
 * The `RotateSettings` class is an open class that represents the settings for rotating images.
 * It extends the `TransformSettings` interface.
 *
 * @property degree The degree of rotation as a double value. The default value is 0.0.
 * @property colour The color to fill the background with during rotation as an instance of the `Color` class. The default value is `Colors.Transparent.toAWT()`.
 * @constructor Creates an instance of the `RotateSettings` class.
 *
 * @see [link](https://sksamuel.github.io/scrimage/rotate/)
 *
 */
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
