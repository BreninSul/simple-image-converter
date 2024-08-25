package io.github.breninsul.simpleimageconvertor.dto.writer

import io.github.breninsul.simpleimageconvertor.dto.settings.WriterSettings

open class WebpWriterSettings(
    val z: Int = -1,
    val q: Int = -1,
    val m: Int = -1,
    val lossless: Boolean = false,
    val noAlpha: Boolean = false,
    val multiThread: Boolean = false,
    ) : WriterSettings