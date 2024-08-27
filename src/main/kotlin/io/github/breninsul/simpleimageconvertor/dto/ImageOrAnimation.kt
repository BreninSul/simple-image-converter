/*
 * MIT License
 * Copyright (c) 2024 BreninSul
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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