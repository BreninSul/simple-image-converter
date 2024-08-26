package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.*

/**
 * Represents a class that writes images in the IFF format using the TwelveMonkeys library.
 *
 * @param order The order of the writer. Default value is 1.
 *
 * @constructor Creates a new instance of the IffWriter class with the given order.
 *
 * @see AbstractTwelveMonkeysWriterWriter
 */
open class IffWriter(order:Int = 1) : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.IFF),"iff",order)