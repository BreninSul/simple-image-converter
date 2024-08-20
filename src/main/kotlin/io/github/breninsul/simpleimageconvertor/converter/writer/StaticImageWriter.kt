package io.github.breninsul.simpleimageconvertor.converter.writer

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.AnimatedGif
import io.github.breninsul.simpleimageconvertor.dto.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.Settings
import java.io.OutputStream
import java.util.function.Supplier

interface StaticImageWriter:ImageWriter {
    fun List<Settings>.getAnimationSettings():AnimationToStaticSettings{
        return this.firstOrNull{it is AnimationToStaticSettings} as AnimationToStaticSettings? ?: AnimationToStaticSettings()
    }

    override fun write(image: ConvertableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        if (image.isAnimation()){
            writeAnimation(image.animation!!,settings.getAnimationSettings(),settings,out)
        } else {
            writeStatic(image.image!!,settings,out)
        }
    }

    fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>)

    fun writeAnimation(animation: AnimatedGif,animationSettings:AnimationToStaticSettings, settings: List<Settings>, out: Supplier<OutputStream>){
        when(animationSettings.strategy){
            AnimationToStaticSettings.StrategyEnum.FIRST_FRAME->writeStatic(animation.frames.first()!!,settings, out)
            AnimationToStaticSettings.StrategyEnum.LAST_FRAME->writeStatic(animation.frames.last()!!,settings, out)
            AnimationToStaticSettings.StrategyEnum.SPECIFIC_FRAME->writeStatic(animation.frames.getOrNull(animationSettings.useFrame.toInt())?:animation.frames.last(),settings, out)
        }
    }

}