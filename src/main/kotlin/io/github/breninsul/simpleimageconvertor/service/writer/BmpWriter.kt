package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat


/**
 * Represents a class for writing images in BMP format using the
 * TwelveMonkeys library.
 *
 * This class is an implementation of the AbstractTwelveMonkeysWriterWriter
 * class, which provides common functionality for writing static images.
 *
 * @param supportedImageTypes The set of ImageFormats supported by this
 *    writer.
 * @param writerFormatName The format name of the writer.
 */
open class BmpWriter : AbstractTwelveMonkeysWriterWriter(setOf(ImageFormat.BMP), "bmp")