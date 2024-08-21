package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat



open class BmpWriter : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.BMP),"bmp")