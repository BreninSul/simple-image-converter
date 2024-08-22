package io.github.breninsul.simpleimageconvertor.dto

import com.sksamuel.scrimage.AwtImage
import kotlin.math.roundToInt

open class Resolution(
    open val width: Int,
    open val height: Int,
    open val keepAspectRatio: Boolean = true,
)  {

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