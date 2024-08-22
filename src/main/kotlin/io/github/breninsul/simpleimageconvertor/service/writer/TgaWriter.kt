package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.*

open class TgaWriter(order:Int = 1) : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.TGA),"tga",order)