package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat

/**
 * Represents a PNM (Portable Any Map) image writer class that extends
 * the AbstractTwelveMonkeysWriterWriter class and implements the
 * StaticImageWriter interface.
 *
 * @param order The order of the writer. Default value is 1.
 * @see AbstractTwelveMonkeysWriterWriter
 * @see StaticImageWriter
 */
open class PnmWriter(order: Int = 1) : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.PNM), "pnm", order)