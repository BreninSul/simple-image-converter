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

package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.AnimatedGif
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.writer.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.extensions.middle
import java.io.OutputStream
import java.util.function.Supplier

/**
 * Represents an interface for writing static images. Extends the
 * ImageWriter interface.
 */
interface StaticImageWriter : ImageWriter {
    /**
     * Returns the AnimationToStaticSettings object from the list of settings,
     * if it exists. If multiple AnimationToStaticSettings objects exist in the
     * list, the first one encountered will be returned.
     *
     * @return the AnimationToStaticSettings object, or null if it does not
     *    exist in the list of settings
     */
    fun List<Settings>.getAnimationToStaticSettings(): AnimationToStaticSettings? {
        return this.firstOrNull { it is AnimationToStaticSettings } as AnimationToStaticSettings?
    }

    override fun writeInternal(image: ImageOrAnimation, settings: List<Settings>, out: OutputStream) {
        if (image.isAnimation()) {
            writeAnimationToStatic(image.animation!!, settings.getAnimationToStaticSettings() ?: AnimationToStaticSettings(), settings, out)
        } else {
            writeStatic(image.image!!, settings, out)
        }
    }

    /**
     * Writes a static image to the specified output stream using the given settings.
     *
     * @param image the `ImmutableImage` to be written as a static image
     * @param settings a list of `Settings` that define how the image should be written
     * @param out the output stream to which the static image will be written
     */
    fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: OutputStream)

    /**
     * Converts an animated GIF to a static image based on the provided settings and writes it to the output stream.
     *
     * @param animation the `AnimatedGif` object representing the animation to be converted
     * @param animationSettings the `AnimationToStaticSettings` object defining the strategy and frame for conversion
     * @param settings a list of additional settings that define how the static image should be written
     * @param out the output stream to which the resulting static image will be written
     */
    fun writeAnimationToStatic(animation: AnimatedGif, animationSettings: AnimationToStaticSettings, settings: List<Settings>, out: OutputStream) {
        when (animationSettings.strategy) {
            AnimationToStaticSettings.StrategyEnum.FIRST_FRAME -> writeStatic(animation.frames.first(), settings, out)
            AnimationToStaticSettings.StrategyEnum.MIDDLE_FRAME -> writeStatic(animation.frames.middle(), settings, out)
            AnimationToStaticSettings.StrategyEnum.LAST_FRAME -> writeStatic(animation.frames.last(), settings, out)
            AnimationToStaticSettings.StrategyEnum.SPECIFIC_FRAME -> writeStatic(animation.frames.getOrNull(animationSettings.useFrame.toInt()) ?: animation.frames.last(), settings, out)
        }
    }
}