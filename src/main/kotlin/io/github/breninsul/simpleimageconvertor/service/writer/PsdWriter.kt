package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.*

open class PsdWriter(order:Int = 1) : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.PSD),"psd",order)