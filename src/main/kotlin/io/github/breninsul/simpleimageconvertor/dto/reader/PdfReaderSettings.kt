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

package io.github.breninsul.simpleimageconvertor.dto.reader

import org.apache.pdfbox.rendering.ImageType
import org.apache.pdfbox.rendering.RenderDestination

/**
 * PdfReaderSettings represents the settings for reading and converting PDF
 * files.
 *
 * @param scale The scale factor to apply when rendering the PDF pages.
 *    Default value is 1.0.
 * @param imageType The type of image to generate from the PDF pages.
 *    Default value is ImageType.RGB.
 * @param destination The destination where the generated images should be
 *    saved. Default value is RenderDestination.EXPORT.
 * @param animationDelay The delay between frames in an animated GIF
 *    generated from multiple PDF pages. Default value is 2000
 *    milliseconds.
 * @constructor Creates a PdfReaderSettings object with the specified
 *    settings.
 * @see ReaderSettings
 */
open class PdfReaderSettings(
    val scale: Float = 1f, val imageType: ImageType = ImageType.RGB, val destination: RenderDestination = RenderDestination.EXPORT, val animationDelay: java.time.Duration = java.time.Duration.ofMillis(2000)
) : ReaderSettings