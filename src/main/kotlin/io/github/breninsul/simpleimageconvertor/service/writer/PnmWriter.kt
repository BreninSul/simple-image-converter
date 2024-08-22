package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.*

open class PnmWriter(order:Int = 1) : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.PNM),"pnm",order)