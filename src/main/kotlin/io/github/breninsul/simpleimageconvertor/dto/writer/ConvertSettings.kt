package io.github.breninsul.simpleimageconvertor.dto.writer

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat

/**
 * The `ConvertSettings` class represents the settings for converting an
 * image to a specific format. It is an open class that can be extended to
 * provide additional functionality.
 *
 * @param format The desired image format for conversion. The default
 *    format is WEBP.
 * @constructor Creates a new instance of `ConvertSettings` with the
 *    specified format.
 * @property format The desired image format for conversion. The default
 *    format is WEBP.
 * @see ImageFormat
 * @see WriterSettings
 */
open class ConvertSettings(
    open val format: ImageFormat = ImageFormat.WEBP,
) : WriterSettings