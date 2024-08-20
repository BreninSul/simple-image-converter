package io.github.breninsul.simpleimageconvertor.dto

open class WebpWriterSettings(
    val z: Int = -1,
    val q: Int = -1,
    val m: Int = -1,
    val lossless: Boolean = false,
    val noAlpha: Boolean = false,
    val multiThread: Boolean = false
) : Settings