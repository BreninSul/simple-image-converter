package io.github.breninsul.simpleimageconvertor.dto.writer

open class JpegWriterSettings(
    val compressionLevel: Int=80,
    val progressive:Boolean=false,
) : WriterSettings