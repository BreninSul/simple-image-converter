package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.TrimTransformer

/**
 * The `TrimSettings` class represents the settings for trimming an image.
 * It extends the `TransformSettings` interface.
 *
 * @constructor Creates a new `TrimSettings` object.
 * @property left The number of pixels to trim from the left side of the
 *    image. Default is 0.
 * @property right The number of pixels to trim from the right side of the
 *    image. Default is 0.
 * @property top The number of pixels to trim from the top of the image.
 *    Default is 0.
 * @property bottom The number of pixels to trim from the bottom of the
 *    image. Default is 0.
 * @see [link](https://sksamuel.github.io/scrimage/trim/)
 */
open class TrimSettings(
    open val left: Int = 0,
    open val right: Int = 0,
    open val top: Int = 0,
    open val bottom: Int = 0,
) : TransformSettings {

    override fun createTransformer() = transformer

    companion object {
        protected val transformer = TrimTransformer()
    }
}