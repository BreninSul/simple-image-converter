package io.github.breninsul.simpleimageconvertor.converter.writer

import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.ImageFormatEnum
import io.github.breninsul.simpleimageconvertor.dto.Settings
import org.springframework.core.Ordered
import java.io.OutputStream
import java.util.function.Supplier

interface ImageWriter : Ordered {
    fun supports(mediaType: ImageFormatEnum): Boolean {
        return supportedTypes().any { mediaType == it }
    }

    fun supportedTypes(): Set<ImageFormatEnum>
    fun write(image: ConvertableImage, settings: List<Settings>, out: Supplier<OutputStream>)

    fun supportsAnimation(): Boolean = false


}

inline fun <reified T : Settings> List<Settings>.getSetting(): T? {
    return this.firstOrNull { it is T } as T?
}
