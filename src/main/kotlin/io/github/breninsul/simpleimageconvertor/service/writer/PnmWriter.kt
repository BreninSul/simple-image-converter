package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.*

open class PnmWriter : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.PNM),"pnm")