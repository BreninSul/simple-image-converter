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

package io.github.breninsul.simpleimageconvertor.service.transformer

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import java.awt.image.BufferedImage

/**
 * Represents an interface for image transformation operations that
 * implement the [ImageTransformer] interface. Provides a method to process
 * a static image using the provided settings. Also provides a method to
 * process an image using the provided settings.
 */
fun interface ImageIOTransformer : ImageTransformer {
    override fun processStatic(image: ImmutableImage, settings: List<Settings>): ImmutableImage {
        return ImmutableImage.fromAwt(processIO(image.awt()))
    }

    /**
     * Processes the given image using the provided settings.
     *
     * @param image The image to process. It must be an instance of
     *    [BufferedImage].
     * @return The processed image as an instance of [BufferedImage].
     */
    fun processIO(image: BufferedImage): BufferedImage
}
