package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.AwtImage
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

    open fun AwtImage.countAspectRatio(): Double = this.width.toDouble() / this.height.toDouble()

}