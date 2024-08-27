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
 * A class that represents the settings for writing WebP files.
 *
 * @param z The compression level. Default value is -1.
 * @param q The quality level. Default value is -1.
 * @param m The method. Default value is -1.
 * @param lossless Whether to enable lossless compression. Default value is
 *    false.
 * @param noAlpha Whether to disable alpha channel. Default value is false.
 * @param multiThread Whether to use multi-threaded encoding. Default value
 *    is false.
 * @constructor Creates a new instance of the WebpWriterSettings class.
 * @see WriterSettings
 */
open class WebpWriterSettings(
    val z: Int = -1,
    val q: Int = -1,
    val m: Int = -1,
    val lossless: Boolean = false,
    val noAlpha: Boolean = false,
    val multiThread: Boolean = false,
) : WriterSettings