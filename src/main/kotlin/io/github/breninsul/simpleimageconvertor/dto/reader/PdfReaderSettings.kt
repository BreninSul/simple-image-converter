package io.github.breninsul.simpleimageconvertor.dto.reader

import org.apache.pdfbox.rendering.ImageType
import org.apache.pdfbox.rendering.RenderDestination

/**
 * PdfReaderSettings represents the settings for reading and converting PDF
 * files.
 *
 * @param scale The scale factor to apply when rendering the PDF pages.
 *    Default value is 1.0.
 * @param imageType The type of image to generate from the PDF pages.
 *    Default value is ImageType.RGB.
 * @param destination The destination where the generated images should be
 *    saved. Default value is RenderDestination.EXPORT.
 * @param animationDelay The delay between frames in an animated GIF
 *    generated from multiple PDF pages. Default value is 2000
 *    milliseconds.
 * @constructor Creates a PdfReaderSettings object with the specified
 *    settings.
 * @see ReaderSettings
 */
open class PdfReaderSettings(
    val scale: Float = 1f, val imageType: ImageType = ImageType.RGB, val destination: RenderDestination = RenderDestination.EXPORT, val animationDelay: java.time.Duration = java.time.Duration.ofMillis(2000)
) : ReaderSettings