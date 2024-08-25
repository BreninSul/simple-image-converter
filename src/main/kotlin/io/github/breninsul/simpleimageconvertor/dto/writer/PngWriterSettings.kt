package io.github.breninsul.simpleimageconvertor.dto.writer

import io.github.breninsul.simpleimageconvertor.dto.settings.WriterSettings

open class PngWriterSettings(
    val compressionLevel: Int=9,
) : WriterSettings