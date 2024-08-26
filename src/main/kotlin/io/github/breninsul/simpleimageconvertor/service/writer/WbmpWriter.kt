package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.*

/**
 * Represents a class for writing images in WBMP format using the TwelveMonkeys library.
 *
 * @param order The order of the writer. Default value is 1.
 *
 * @constructor Creates a new instance of the WbmpWriter class with the given order.
 *
 * @see AbstractTwelveMonkeysWriterWriter
 * @see ImageFormat.WBMP
 */
open class WbmpWriter(order:Int = 1) : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.WBMP),"wbmp",order)