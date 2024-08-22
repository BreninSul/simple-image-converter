package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.*


open class IffWriter(order:Int = 1) : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.IFF),"iff",order)