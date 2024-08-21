package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.*


open class IffWriter : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.IFF),"iff")