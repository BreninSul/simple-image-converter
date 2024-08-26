package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.*

/**
 * Represents a class for writing images in PICT format using the TwelveMonkeys library.
 * It extends the AbstractTwelveMonkeysWriterWriter class and implements the StaticImageWriter interface.
 *
 * @param order The order of the writer. Default value is 1.
 *
 * @see AbstractTwelveMonkeysWriterWriter
 * @see StaticImageWriter
 */
open class PictWriter(order:Int = 1) : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.PICT),"Pict",order)