package io.github.breninsul.simpleimageconvertor.dto.writer

import io.github.breninsul.simpleimageconvertor.dto.WriterSettings

open class JpegWriterSettings(
    val compressionLevel: Int=80,
    val progressive:Boolean=false,
) : WriterSettings