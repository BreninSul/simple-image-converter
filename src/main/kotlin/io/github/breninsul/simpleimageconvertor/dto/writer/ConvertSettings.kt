package io.github.breninsul.simpleimageconvertor.dto.writer

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat


open class ConvertSettings(
    open val format: ImageFormat = ImageFormat.WEBP,
) : WriterSettings