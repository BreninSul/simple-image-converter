package io.github.breninsul.simpleimageconvertor.dto.reader

import org.apache.pdfbox.rendering.ImageType
import org.apache.pdfbox.rendering.RenderDestination

open class PdfReaderSettings (
    val scale:Float=1f, val imageType: ImageType=ImageType.RGB, val destination: RenderDestination=RenderDestination.EXPORT, val animationDelay:java.time.Duration=java.time.Duration.ofMillis(2000)
): ReaderSettings