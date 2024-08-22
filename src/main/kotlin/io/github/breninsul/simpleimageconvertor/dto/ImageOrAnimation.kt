package io.github.breninsul.simpleimageconvertor.dto

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay
import io.github.breninsul.simpleimageconvertor.exception.ImageException

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