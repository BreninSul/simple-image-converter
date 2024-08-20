package io.github.breninsul.simpleimageconvertor.converter.reader

import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.Settings
import org.springframework.core.Ordered
import java.io.InputStream
import java.util.function.Supplier

interface ImageReader : Ordered {
    fun read(fileStream: Supplier<InputStream>,settings: List<Settings>): ConvertableImage
    fun supports(mediaType: String): Boolean {
        val lowercase = mediaType.lowercase()
        return supportedTypes().any { lowercase.endsWith(it) }
    }

    fun supportedTypes(): Set<String>
}
