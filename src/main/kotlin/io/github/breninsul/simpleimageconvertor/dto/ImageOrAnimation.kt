package io.github.breninsul.simpleimageconvertor.dto

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay
import io.github.breninsul.simpleimageconvertor.exception.ImageException

/**
 * Represents an image or animation. It contains either an
 * AnimatedGifWithDelay object or an ImmutableImage object.
 *
 * @property animation The AnimatedGifWithDelay object representing an
 *    animation. Null if the object represents an image.
 * @property image The ImmutableImage object representing an image. Null if
 *    the object represents an animation.
 * @throws ImageException if both animation and image are null or not null
 * @see AnimatedGifWithDelay
 * @see ImmutableImage
 */
open class ImageOrAnimation(
    val animation: AnimatedGifWithDelay?,
    val image: ImmutableImage?
) {
    init {
        if (animation == null && image == null) {
            throw ImageException("Both animation and image is null")
        }
        if (animation != null && image != null) {
            throw ImageException("Both animation and image not null")
        }
    }

    open fun isAnimation(): Boolean {
        return animation != null
    }
}