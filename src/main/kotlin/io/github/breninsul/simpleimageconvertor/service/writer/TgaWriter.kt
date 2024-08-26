package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.*

/**
 * This class represents a TGA writer, which is used to write images in TGA format.
 * It extends the AbstractTwelveMonkeysWriterWriter class and implements the StaticImageWriter interface.
 *
 * @param order The order of the TGA writer. Default value is 1.
 *
 * @see AbstractTwelveMonkeysWriterWriter
 * @see StaticImageWriter
 */
open class TgaWriter(order:Int = 1) : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.TGA),"tga",order)