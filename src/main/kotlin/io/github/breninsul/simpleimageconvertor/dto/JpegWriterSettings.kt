package io.github.breninsul.simpleimageconvertor.dto

open class JpegWriterSettings(
    val compressionLevel: Int=80,
    val progressive:Boolean=false,
) : Settings