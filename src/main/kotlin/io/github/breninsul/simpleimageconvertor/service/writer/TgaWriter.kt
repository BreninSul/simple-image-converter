package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.*

open class TgaWriter : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.TGA),"tga")