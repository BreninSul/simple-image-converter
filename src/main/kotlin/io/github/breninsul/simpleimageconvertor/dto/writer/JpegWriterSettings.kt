package io.github.breninsul.simpleimageconvertor.dto.writer

/**
 * The `JpegWriterSettings` class represents the settings for writing JPEG
 * files. It extends the `WriterSettings` interface.
 *
 * @property compressionLevel The compression level of the JPEG file,
 *    ranging from 0 to 100. Higher values result in smaller file sizes but
 *    lower image quality.
 * @property progressive Indicates whether the JPEG file should be saved in
 *    progressive mode. Progressive mode allows partial rendering of the
 *    image when it is being loaded, giving the appearance of a faster
 *    loading time.
 */
open class JpegWriterSettings(
    val compressionLevel: Int = 80,
    val progressive: Boolean = false,
) : WriterSettings