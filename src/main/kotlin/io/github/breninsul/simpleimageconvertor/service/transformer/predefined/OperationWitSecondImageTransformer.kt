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

package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.DisposeMethod
import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.OperationWithImageSettings
import io.github.breninsul.simpleimageconvertor.dto.writer.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.extensions.middle
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer.Companion.logger
import java.time.Duration
import java.util.logging.Level
import kotlin.reflect.KClass

/**
 * This abstract class represents a transformer that performs
 * image processing operations using a second image as part of the
 * transformation. It extends the `PredefinedTransformer` class and
 * provides implementations for processing animated GIFs with delays and
 * static images.
 *
 * @param T The type of the operation settings. It must extend the
 *    `OperationWithImageSettings` class.
 * @property clazz The class of the operation settings type. It is used to
 *    identify and fetch the appropriate settings from the list of
 *    settings.
 */
abstract class OperationWitSecondImageTransformer<T : OperationWithImageSettings>(clazz: KClass<T>) : PredefinedTransformer<T>(clazz) {

    override fun AnimatedGifWithDelay.processAnimation(settings: List<Settings>): AnimatedGifWithDelay {
        val srcFrames = getFramesWithDelay()
        val operationSettings = getOperationSettings(settings)
        val newFrames = srcFrames.mapIndexed { index, imageWithDelay ->
            val destFrame = resolveDestinationFrame(srcFrames, index, imageWithDelay, operationSettings)
            AnimatedGifWithDelay.ImageWithDelay(processTransformation(imageWithDelay.toImmutableImage(), mapOptionsToFrame(operationSettings, destFrame)).awt(), imageWithDelay.delay, imageWithDelay.disposeMethod)
        }
        return this.withFrames(newFrames)
    }

    override fun processStatic(image: ImmutableImage, settings: List<Settings>): ImmutableImage {
        val destFrame = resolveDestinationFrame(image, getOperationSettings(settings))
        return processTransformation(image, mapOptionsToFrame(getOperationSettings(settings), destFrame))
    }

    /**
     * This protected abstract method is used to map the options to a frame
     * in image processing operations. The options are represented by the
     * settings of type T, and the frame image is represented by the frameImage
     * parameter of type ImmutableImage. The method returns an instance of the
     * transform settings type T.
     *
     * @param settings The settings of type T representing the options to be
     *    mapped to the frame.
     * @param frameImage The frame image to which the options need to be
     *    mapped.
     * @return An instance of the transform settings type T after mapping the
     *    options to the frame.
     */
    protected abstract fun mapOptionsToFrame(settings: T, frameImage: ImmutableImage): T

    /**
     * Resolves the destination frame for image processing operations.
     *
     * This method takes a source image and a settings object of type T.
     * It creates a no-delay frame from the source image and passes it
     * along with the settings to the overloaded version of the function,
     * [resolveDestinationFrame].
     *
     * @param sourceImage The source image for which the destination frame
     *    needs to be resolved.
     * @param settings The settings object of type T representing the options
     *    for image processing.
     * @return The resolved destination frame as an instance of
     *    [ImmutableImage].
     */
    protected open fun resolveDestinationFrame(sourceImage: ImmutableImage, settings: T): ImmutableImage {
        val noDelayFrame = AnimatedGifWithDelay.ImageWithDelay(sourceImage.awt(), Duration.ZERO, DisposeMethod.NONE)
        return resolveDestinationFrame(listOf(noDelayFrame), 0, noDelayFrame, settings)
    }

    /**
     * Resolves the destination frame for image processing operations.
     *
     * This method takes a list of source frames, the index of the source frame
     * to be processed, the source image with delay, and the settings object
     * of type T. It calculates the destination frame based on the source
     * frame's delay, the destination animation's frames and delays, and the
     * given settings. If the source image is not an animation, it returns
     * the original image from the settings. If the destination animation
     * has no delays, it applies default animation to static settings and
     * returns the corresponding frame. Otherwise, it determines the adopted
     * source animation's total duration, calculates the delay for the
     * source image, and finds the corresponding destination frame using the
     * calculated delay. Finally, it returns the resolved destination frame.
     *
     * @param srcFrames The list of source frames as instances of
     *    [AnimatedGifWithDelay.ImageWithDelay].
     * @param index The index of the source frame to be processed.
     * @param sourceImageWithDelay The source image with delay as an instance
     *    of [AnimatedGifWithDelay.ImageWithDelay].
     * @param settings The settings object of type T representing the options
     *    for image processing.
     * @return The resolved destination frame as an instance of
     *    [ImmutableImage].
     */
    protected open fun resolveDestinationFrame(srcFrames: List<AnimatedGifWithDelay.ImageWithDelay>, index: Int, sourceImageWithDelay: AnimatedGifWithDelay.ImageWithDelay, settings: T): ImmutableImage {
        if (settings.image.isAnimation()) {
            val destinationAnimation = settings.image.animation!!
            if (settings.animationToStaticSettings != null) {
                return resolveAnimationToStaticImage(settings.animationToStaticSettings!!, destinationAnimation)
            }

            val destFrames = destinationAnimation.getFramesWithDelay()
            val destinationAnimationTotalDuration = destFrames.sumOf { it.delay.toMillis() }
            //No delays, return with default animation to static settings
            if (destinationAnimationTotalDuration < 1) {
                return resolveAnimationToStaticImage(AnimationToStaticSettings(), destinationAnimation)
            }
            val delayBeforeSrcImage = srcFrames.filterIndexed { idx, _ -> idx < index }.sumOf { it.delay.toMillis() }
            val adoptedSrcAnimationTotalDuration = if (delayBeforeSrcImage > destinationAnimationTotalDuration) delayBeforeSrcImage % destinationAnimationTotalDuration else delayBeforeSrcImage
            val delaySrcImage = adoptedSrcAnimationTotalDuration..adoptedSrcAnimationTotalDuration + sourceImageWithDelay.delay.toMillis()
            if (delaySrcImage.last < 1) {
                return destinationAnimation.frames.first()
            }
            val destFrameInTeSameDelay = destFrames.fold(0L to destFrames.first()) { oldFrame, newFrame ->
                if (delaySrcImage.contains(oldFrame.first)) {
                    return@fold oldFrame
                } else {
                    return@fold oldFrame.first + newFrame.delay.toMillis() to newFrame
                }
            }
            logger.log(Level.FINER, "Dest frame with delay ${destFrameInTeSameDelay.first} (total $destinationAnimationTotalDuration) was chosen for src image with delay $delayBeforeSrcImage")
            return destFrameInTeSameDelay.second.toImmutableImage()
        } else {
            return settings.image.image!!
        }
    }


    /**
     * Resolves the destination frame for image processing operations by
     * converting an animation to a static image.
     *
     * @param settings The settings object of type AnimationToStaticSettings
     *    representing the options for converting the animation to a static
     *    image.
     * @param destinationAnimation The destination animation as an instance of
     *    AnimatedGifWithDelay.
     * @return The resolved destination frame as an instance of ImmutableImage.
     */
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
