package io.github.breninsul.simpleimageconvertor.dto

import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode
import org.apache.pdfbox.pdmodel.common.PDRectangle


open class PdfWriterSettings (
    val rectangle: PDRectangle=PDRectangle.A4,
    val imageFormat: ImageFormatEnum=ImageFormatEnum.WEBP,
    val compress:Boolean=false,
    val appendMode:AppendMode=AppendMode.APPEND,
):Settings