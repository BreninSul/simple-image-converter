package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat

/**
 * Represents a class for writing ICNS images using the TwelveMonkeys
 * library.
 *
 * @param order The order of the ICNS writer. The default value is 1.
 * @constructor Creates an instance of the IcnsWriter class with the given
 *    order.
 * @see AbstractTwelveMonkeysWriterWriter
 * @see ImageFormat.ICNS
 */
open class IcnsWriter(order: Int = 1) : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.ICNS), "icns", order)