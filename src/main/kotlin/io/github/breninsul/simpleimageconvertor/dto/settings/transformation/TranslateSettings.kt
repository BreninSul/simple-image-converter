package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.TranslateTransformer
import java.awt.Color

/**
 * The `TranslateSettings` class is an open class that represents the settings for performing translation on an image.
 * It provides default values for the translation coordinates and colour.
 *
 * @property x The translation distance in the x-axis. Default value is 0.
 * @property y The translation distance in the y-axis. Default value is 0.
 * @property colour The colour used to fill the empty space created by the translation. Default value is transparent.
 * @constructor Creates a `TranslateSettings` instance.
 *
 * @see [link](https://sksamuel.github.io/scrimage/translate/)
 */
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