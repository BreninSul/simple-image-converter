package io.github.breninsul.simpleimageconvertor.dto.writer

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.WriterSettings
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode
import org.apache.pdfbox.pdmodel.common.PDRectangle


open class PdfWriterSettings (
    val rectangle: PDRectangle=PDRectangle.A4,
    val imageFormat: ImageFormat = ImageFormat.PNG,
    val compress:Boolean=false,
    val appendMode:AppendMode=AppendMode.APPEND,
): WriterSettings