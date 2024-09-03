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

package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.AwtImage
import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import kotlin.math.roundToInt

/**
 * The `Resolution` class represents the resolution of an image or
 * animation. It stores the width and height of the resolution and has an
 * optional flag for maintaining aspect ratio.
 *
 * @constructor Creates a new instance of `Resolution` with the specified
 *    width and height.
 * @property width The width of the resolution.
 * @property height The height of the resolution.
 * @property keepAspectRatio Flag indicating whether to maintain the aspect
 *    ratio when resolving the resolution.
 * @see ImageOrAnimation
 * @see AwtImage
 */
open class Resolution(
    open val width: Int,
    open val height: Int,
    open val keepAspectRatio: Boolean = true,
) {

    open fun ImageOrAnimation.resolveResolutionWithOriginalAspectRate(): Resolution {
        if (!keepAspectRatio) return Resolution(width, height)
        val destinationAspectRatio = width.toDouble() / height.toDouble()
        val originalAspectRatio = if (this.isAnimation()) {
            this.animation!!.frames.firstOrNull()?.countAspectRatio()
        } else {
            this.image!!.countAspectRatio()
        }
        if (originalAspectRatio == null) return this@Resolution
        return if (destinationAspectRatio == originalAspectRatio) {
            Resolution(width, height)
        } else if (destinationAspectRatio < originalAspectRatio) {
            val height = width / originalAspectRatio
            Resolution(width, height.roundToInt())
        } else {
            val width = height * originalAspectRatio
            Resolution(width.roundToInt(), height)
        }
    }
    open fun resolveResolutionWithOriginalAspectRate(image:ImmutableImage): Resolution {
        if (!keepAspectRatio) return Resolution(width, height)
        val destinationAspectRatio = width.toDouble() / height.toDouble()
        val originalAspectRatio = image.countAspectRatio()
        if (originalAspectRatio == null) return this@Resolution
        return if (destinationAspectRatio == originalAspectRatio) {
            Resolution(width, height)
        } else if (destinationAspectRatio < originalAspectRatio) {
            val height = width / originalAspectRatio
            Resolution(width, height.roundToInt())
        } else {
            val width = height * originalAspectRatio
            Resolution(width.roundToInt(), height)
        }
    }
    open fun AwtImage.countAspectRatio(): Double = this.width.toDouble() / this.height.toDouble()

}