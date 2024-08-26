package io.github.breninsul.simpleimageconvertor.dto.writer

/**
 * A class that represents the settings for writing WebP files.
 *
 * @param z The compression level. Default value is -1.
 * @param q The quality level. Default value is -1.
 * @param m The method. Default value is -1.
 * @param lossless Whether to enable lossless compression. Default value is false.
 * @param noAlpha Whether to disable alpha channel. Default value is false.
 * @param multiThread Whether to use multi-threaded encoding. Default value is false.
 *
 * @constructor Creates a new instance of the WebpWriterSettings class.
 *
 * @see WriterSettings
 */
open class WebpWriterSettings(
    val z: Int = -1,
    val q: Int = -1,
    val m: Int = -1,
    val lossless: Boolean = false,
    val noAlpha: Boolean = false,
    val multiThread: Boolean = false,
) : WriterSettings