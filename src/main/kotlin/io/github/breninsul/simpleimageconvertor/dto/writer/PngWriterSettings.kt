package io.github.breninsul.simpleimageconvertor.dto.writer

/**
 * The `PngWriterSettings` class represents the settings for writing PNG
 * files using the `PngWriter` class. It extends the `WriterSettings`
 * interface.
 *
 * @property compressionLevel The compression level to use when writing the
 *    PNG file. Default is 9.
 */
open class PngWriterSettings(
    val compressionLevel: Int = 9,
) : WriterSettings