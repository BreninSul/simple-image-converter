package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.nio.AnimatedGif
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import java.io.OutputStream
import java.util.function.Supplier

/**
 * AnimationImageWriter is an interface that represents an image writer specifically designed for handling animations.
 * It extends the StaticImageWriter interface and provides additional functionality for writing animated images.
 */
interface AnimationImageWriter:StaticImageWriter {
    override fun write(image: ImageOrAnimation, settings: List<Settings>, out: Supplier<OutputStream>) {
        if (image.isAnimation()){
            val animationToStaticSetting=settings.getAnimationToStaticSettings()
            if (animationToStaticSetting!=null) {
                writeAnimationToStatic(image.animation!!, animationToStaticSetting, settings, out)
            } else{
                writeAnimation(image.animation!!,settings,out)
            }
        } else {
            writeStatic(image.image!!,settings,out)
        }
    }

    /**
     * Writes an animation to an output stream using the specified settings.
     *
     * @param animation the AnimatedGif to write
     * @param settings the list of Settings to apply during the writing process
     * @param out the Supplier of OutputStream to write the animation to
     */
    fun writeAnimation(animation: AnimatedGif, settings: List<Settings>, out: Supplier<OutputStream>)
}