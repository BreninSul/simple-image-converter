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

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode
import org.apache.pdfbox.pdmodel.common.PDRectangle

/**
 * The `PdfWriterSettings` class represents the settings for the
 * `PdfWriter` class, which is used to write PDF files.
 *
 * @param rectangle The page size of the PDF document. The default value is
 *    [PDRectangle.A4].
 * @param imageFormat The image format to use when writing images to the
 *    PDF document. The default value is [ImageFormat.PNG].
 * @param compress Specifies whether to compress the PDF document. The
 *    default value is false.
 * @param appendMode The mode to use when appending pages to an existing
 *    PDF document. The default value is [AppendMode.APPEND].
 * @property rectangle The page size of the PDF document.
 * @property imageFormat The image format to use when writing images to the
 *    PDF document.
 * @property compress Specifies whether to compress the PDF document.
 * @property appendMode The mode to use when appending pages to an existing
 *    PDF document.
 * @see [PDRectangle]
 * @see [ImageFormat]
 * @see [AppendMode]
 * @see [PdfWriter]
 * @see [WriterSettings]
 */
open class PdfWriterSettings(
    val rectangle: PDRectangle = PDRectangle.A4,
    val imageFormat: ImageFormat = ImageFormat.PNG,
    val compress: Boolean = false,
    val appendMode: AppendMode = AppendMode.APPEND,
) : WriterSettings