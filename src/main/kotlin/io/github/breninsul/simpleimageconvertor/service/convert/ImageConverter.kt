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

package io.github.breninsul.simpleimageconvertor.service.convert

import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import java.io.OutputStream
import java.util.function.Supplier

/**
 * Interface for converting an image using specified settings and writing
 * the output to a given output stream.
 */
interface ImageConverter {
    /**
     * Converts the given [image] with the specified [settings] and writes the
     * output to the [outputSupplier].
     *
     * @param image The image to be converted. It must be an instance of
     *    [ImageOrAnimation].
     * @param settings The list of settings to be applied during the
     *    conversion. Each setting must implement the [Settings] interface.
     * @param outputSupplier The supplier of the output stream where the
     *    converted image will be written to.
     */
    fun convert(
        image: ImageOrAnimation,
        settings: List<Settings>,
        outputSupplier: Supplier<OutputStream>
    )

    /**
     * Checks the validity of the provided settings.
     *
     * @param settings The list of settings to be checked. Each setting must
     *    implement the [Settings] interface.
     */
    fun checkSettings(settings: List<Settings>)
}