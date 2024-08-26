package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.DisposeMethod
import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.getSetting
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.OperationWithImageSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.OverlaySettings
import io.github.breninsul.simpleimageconvertor.extensions.middle
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer.Companion.logger
import java.time.Duration
import java.util.logging.Level
import kotlin.reflect.KClass


abstract class OperationWitSecondImageTransformer<T:OperationWithImageSettings> (clazz:KClass<T>): PredefinedTransformer<T>(clazz) {

    override fun AnimatedGifWithDelay.processAnimation(settings: List<Settings>): AnimatedGifWithDelay {
        val srcFrames = getFramesWithDelay()
        val operationSettings=getOperationSettings(settings)
        val newFrames = srcFrames.mapIndexed { index, imageWithDelay ->
            val destFrame=resolveDestinationFrame(srcFrames,index,imageWithDelay,operationSettings)
            AnimatedGifWithDelay.ImageWithDelay(processTransformation(imageWithDelay.toImmutableImage(), mapOptionsToFrame(operationSettings,destFrame)).awt(),imageWithDelay.delay,imageWithDelay.disposeMethod)
        }
        return this.withFrames(newFrames)
    }

    override fun processStatic(image: ImmutableImage, settings: List<Settings>): ImmutableImage {
        val destFrame=resolveDestinationFrame(image,getOperationSettings(settings))
        return processTransformation(image, mapOptionsToFrame(getOperationSettings(settings),destFrame))
    }

    protected abstract fun mapOptionsToFrame(settings: T,frameImage:ImmutableImage):T

    protected open fun resolveDestinationFrame(  sourceImage:ImmutableImage, settings: T): ImmutableImage {
        val noDelayFrame=AnimatedGifWithDelay.ImageWithDelay(sourceImage.awt(), Duration.ZERO,DisposeMethod.NONE)
        return resolveDestinationFrame(listOf(noDelayFrame),0,noDelayFrame,settings)
    }

    protected open fun resolveDestinationFrame(srcFrames: List<AnimatedGifWithDelay.ImageWithDelay>, index: Int, sourceImageWithDelay: AnimatedGifWithDelay.ImageWithDelay,  settings: T): ImmutableImage {
        if (settings.image.isAnimation()) {
            val destinationAnimation = settings.image.animation!!
            if (settings.animationToStaticSettings != null) {
                return resolveAnimationToStaticImage(settings.animationToStaticSettings!!, destinationAnimation)
            }

            val destinationAnimationTotalDuration = destinationAnimation.getFramesWithDelay().sumOf { it.delay.toMillis() }
            //No delays, return with default animation to static settings
            if (destinationAnimationTotalDuration < 1) {
                return resolveAnimationToStaticImage(AnimationToStaticSettings(),destinationAnimation)
            }
            val delayBeforeSrcImage = srcFrames.filterIndexed { idx, _ -> idx < index }.sumOf { it.delay.toMillis() }
            val adoptedSrcAnimationTotalDuration =if (delayBeforeSrcImage > destinationAnimationTotalDuration)  delayBeforeSrcImage % destinationAnimationTotalDuration  else delayBeforeSrcImage
            val delaySrcImage = adoptedSrcAnimationTotalDuration..adoptedSrcAnimationTotalDuration + sourceImageWithDelay.delay.toMillis()
            if (delaySrcImage.last < 1) {
                return destinationAnimation.frames.first()
            }
            val destFrameInTeSameDelay= srcFrames.fold(0L to srcFrames.first()) {
                oldFrame,newFrame->
                if (delaySrcImage.contains(oldFrame.first)){
                    return@fold oldFrame
                } else{
                    return@fold oldFrame.first+newFrame.delay.toMillis() to newFrame
                }
            }
            logger.log(Level.FINER,"Dest frame with delay ${destFrameInTeSameDelay.first} (total $destinationAnimationTotalDuration) was chosen for src image with delay $delayBeforeSrcImage")
            return destFrameInTeSameDelay.second.toImmutableImage()
        } else {
            return settings.image.image!!
        }
    }



    protected open fun resolveAnimationToStaticImage(
        settings: AnimationToStaticSettings,
        destinationAnimation: AnimatedGifWithDelay
    ): ImmutableImage = when (settings.strategy) {
        AnimationToStaticSettings.StrategyEnum.FIRST_FRAME -> destinationAnimation.frames.first()
        AnimationToStaticSettings.StrategyEnum.MIDDLE_FRAME -> destinationAnimation.frames.middle()
        AnimationToStaticSettings.StrategyEnum.LAST_FRAME -> destinationAnimation.frames.last()
        AnimationToStaticSettings.StrategyEnum.SPECIFIC_FRAME -> destinationAnimation.frames.getOrNull(settings.useFrame.toInt()) ?: destinationAnimation.frames.last()
    }

}
