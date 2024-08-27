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

package io.github.breninsul.simpleimageconvertor.dto.writer

/**
 * The `JpegWriterSettings` class represents the settings for writing JPEG
 * files. It extends the `WriterSettings` interface.
 *
 * @property compressionLevel The compression level of the JPEG file,
 *    ranging from 0 to 100. Higher values result in smaller file sizes but
 *    lower image quality.
 * @property progressive Indicates whether the JPEG file should be saved in
 *    progressive mode. Progressive mode allows partial rendering of the
 *    image when it is being loaded, giving the appearance of a faster
 *    loading time.
 */
open class JpegWriterSettings(
    val compressionLevel: Int = 80,
    val progressive: Boolean = false,
) : WriterSettings