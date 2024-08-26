package io.github.breninsul.simpleimageconvertor.dto.writer

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode
import org.apache.pdfbox.pdmodel.common.PDRectangle

/**
 * The `PdfWriterSettings` class represents the settings for the `PdfWriter` class, which is used to write PDF files.
 *
 * @param rectangle The page size of the PDF document. The default value is [PDRectangle.A4].
 * @param imageFormat The image format to use when writing images to the PDF document. The default value is [ImageFormat.PNG].
 * @param compress Specifies whether to compress the PDF document. The default value is false.
 * @param appendMode The mode to use when appending pages to an existing PDF document. The default value is [AppendMode.APPEND].
 *
 * @property rectangle The page size of the PDF document.
 * @property imageFormat The image format to use when writing images to the PDF document.
 * @property compress Specifies whether to compress the PDF document.
 * @property appendMode The mode to use when appending pages to an existing PDF document.
 *
 * @see [PDRectangle]
 * @see [ImageFormat]
 * @see [AppendMode]
 * @see [PdfWriter]
 * @see [WriterSettings]
 */
open class PdfWriterSettings (
    val rectangle: PDRectangle=PDRectangle.A4,
    val imageFormat: ImageFormat = ImageFormat.PNG,
    val compress:Boolean=false,
    val appendMode:AppendMode=AppendMode.APPEND,
): WriterSettings