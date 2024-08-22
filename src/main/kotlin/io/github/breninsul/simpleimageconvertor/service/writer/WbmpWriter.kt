package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.*


open class WbmpWriter(order:Int = 1) : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.WBMP),"wbmp",order)