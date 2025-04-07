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

package io.github.breninsul.simpleimageconvertor.dto.settings.writer

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat

/**
 * The `JpegWriterSettings` class represents the settings for writing JPEG
 * files. It extends the functionality provided by `ConvertSettings` and
 * is specifically tailored to handle JPEG-specific configurations.
 *
 * @param compressionLevel Specifies the compression level for the JPEG
 *    file. The accepted range is typically from 0 (maximum compression)
 *    to 100 (no compression, not acceptable, max is 99). The default value is 80.
 * @param progressive Determines whether the JPEG file should be written in
 *    progressive mode. If set to true, the file will be written in a way
 *    that allows for incremental rendering. The default value is false.
 * @property compressionLevel The defined compression level of the JPEG file.
 * @property progressive Indicates whether progressive mode is enabled for
 *    JPEG output.
 * @see ConvertSettings
 * @see WriterSettings
 * @see ImageFormat
 */
open class JpegWriterSettings(
    val compressionLevel: Int = 80,
    val progressive: Boolean = false,
) : ConvertSettings(format = ImageFormat.JPEG)