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

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.Ordered
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import java.io.OutputStream
import java.util.function.Supplier

/** An interface for writing images in various formats. */
interface ImageWriter : Ordered {
    /**
     * Checks if the specified media type is supported by this image writer.
     *
     * @param mediaType the media type to check
     * @return `true` if the media type is supported, `false` otherwise
     */
    fun supports(mediaType: ImageFormat): Boolean {
        return supportedTypes().any { mediaType.equals(it) }
    }

    /**
     * Returns the set of supported image formats by this image writer.
     *
     * @return the set of supported image formats
     */
    fun supportedTypes(): Set<ImageFormat>

    /**
     * Writes the given ConvertableImage using the specified Settings and
     * outputs the result to the provided OutputStream.
     *
     * @param image the ConvertableImage to write
     * @param settings the list of Settings to apply during the writing process
     * @param out the Supplier of OutputStream to write the image to
     */
    fun write(image: ImageOrAnimation, settings: List<Settings>, out: Supplier<OutputStream>)

    /**
     * Checks if the image writer supports animation.
     *
     * @return `true` if the image writer supports animation, `false` otherwise
     */
    fun supportsAnimation(): Boolean = false


}

