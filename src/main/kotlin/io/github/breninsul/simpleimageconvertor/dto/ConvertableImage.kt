package io.github.breninsul.simpleimageconvertor.dto

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay

open class ConvertableImage(
    val animation: AnimatedGifWithDelay?,
    val image: ImmutableImage?
) {
    init {
        if (animation == null && image == null) {
            throw IllegalStateException("Both animation and image is null")
        }
        if (animation != null && image != null) {
            throw IllegalStateException("Both animation and image not null")
        }
    }

    open fun isAnimation(): Boolean {
        return animation != null
    }
}