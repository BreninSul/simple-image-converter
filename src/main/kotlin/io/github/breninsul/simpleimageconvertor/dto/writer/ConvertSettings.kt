package io.github.breninsul.simpleimageconvertor.dto.writer

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.WriterSettings


open class ConvertSettings(
    open val format: ImageFormat = ImageFormat.WEBP,
) : WriterSettings