package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.Position
import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.FlipTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ResizeToTransformer
import java.awt.Color

/**
 * The `ResizeToSettings` class represents the settings for resizing an image to a specified resolution.
 *
 * @property resolution The resolution to resize the image to. It is an instance of the `Resolution` class.
 * @property position The position of the resized image. It is an instance of the `Position` class.
 * @property colour The background color for the resized image. It is an instance of the `Color` class. The default value is transparent.
 * @constructor Creates an instance of the `ResizeToSettings` class.
 *
 * @see [link](https://sksamuel.github.io/scrimage/resize/)
 */
open class ResizeToSettings(
    open val resolution: Resolution,
    open val position: Position,
    open val colour: Color = Colors.Transparent.toAWT(),
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = ResizeToTransformer()
    }}