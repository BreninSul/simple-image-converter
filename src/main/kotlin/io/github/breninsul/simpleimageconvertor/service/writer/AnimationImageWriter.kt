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

import com.sksamuel.scrimage.nio.AnimatedGif
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import java.io.OutputStream
import java.util.function.Supplier

/**
 * AnimationImageWriter is an interface that represents an image writer
 * specifically designed for handling animations. It extends the
 * StaticImageWriter interface and provides additional functionality for
 * writing animated images.
 */
interface AnimationImageWriter : StaticImageWriter {
    override fun write(image: ImageOrAnimation, settings: List<Settings>, out: Supplier<OutputStream>) {
        if (image.isAnimation()) {
            val animationToStaticSetting = settings.getAnimationToStaticSettings()
            if (animationToStaticSetting != null) {
                writeAnimationToStatic(image.animation!!, animationToStaticSetting, settings, out)
            } else {
                writeAnimation(image.animation!!, settings, out)
            }
        } else {
            writeStatic(image.image!!, settings, out)
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