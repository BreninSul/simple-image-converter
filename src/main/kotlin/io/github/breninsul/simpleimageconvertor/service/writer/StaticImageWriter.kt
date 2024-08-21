package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.AnimatedGif
import io.github.breninsul.simpleimageconvertor.dto.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.Settings
import java.io.OutputStream
import java.util.function.Supplier

/**
 * Represents an interface for writing static images. Extends the ImageWriter interface.
 */
interface StaticImageWriter:ImageWriter {
    /**
     * Returns the AnimationToStaticSettings object from the list of settings, if it exists.
     * If multiple AnimationToStaticSettings objects exist in the list, the first one encountered will be returned.
     *
     * @return the AnimationToStaticSettings object, or null if it does not exist in the list of settings
     */
    fun List<Settings>.getAnimationToStaticSettings():AnimationToStaticSettings?{
        return this.firstOrNull{it is AnimationToStaticSettings} as AnimationToStaticSettings?
    }

    override fun write(image: ConvertableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        if (image.isAnimation()){
            writeAnimationToStatic(image.animation!!,settings.getAnimationToStaticSettings()?: AnimationToStaticSettings(),settings,out)
        } else {
            writeStatic(image.image!!,settings,out)
        }
    }

    /**
     * Writes a static image to an output stream using the specified settings.
     *
     * @param image the ImmutableImage to write
     * @param settings the list of Settings to apply during the writing process
     * @param out the Supplier of OutputStream to write the image to
     * @throws ImageException if both animation and image are null or not null
     */
    fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>)

    /**
     * Writes an animation to a static image based on the specified settings.
     *
     * @param animation the AnimatedGif to write to a static image
     * @param animationSettings the AnimationToStaticSettings object specifying the conversion settings
     * @param settings the list of Settings to apply during the writing process
     * @param out the Supplier of OutputStream to write the static image to
     */
    fun writeAnimationToStatic(animation: AnimatedGif, animationSettings:AnimationToStaticSettings, settings: List<Settings>, out: Supplier<OutputStream>){
        when(animationSettings.strategy){
            AnimationToStaticSettings.StrategyEnum.FIRST_FRAME->writeStatic(animation.frames.first(),settings, out)
            AnimationToStaticSettings.StrategyEnum.MIDDLE_FRAME->writeStatic(animation.frames.middle(),settings, out)
            AnimationToStaticSettings.StrategyEnum.LAST_FRAME->writeStatic(animation.frames.last(),settings, out)
            AnimationToStaticSettings.StrategyEnum.SPECIFIC_FRAME->writeStatic(animation.frames.getOrNull(animationSettings.useFrame.toInt())?:animation.frames.last(),settings, out)
        }
    }
    fun <T> List<T>.middle() :T= this[(this.size /2) + (this.size % 2)]
}