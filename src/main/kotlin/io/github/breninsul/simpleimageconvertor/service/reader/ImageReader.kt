package io.github.breninsul.simpleimageconvertor.service.reader

import io.github.breninsul.simpleimageconvertor.dto.Ordered
import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.Settings
import java.io.InputStream
import java.util.function.Supplier

/**
 * The ImageReader interface provides a way to read and convert images from various file types.
 */
interface ImageReader : Ordered {
    /**
     * Reads an image from a given file stream and applies the specified settings to convert it into a ConvertableImage object.
     *
     * @param fileStream A Supplier of InputStream that represents the file stream from which the image will be read.
     * @param settings A List of Settings objects that specify the settings to be applied during the conversion of the image.
     * @return A ConvertableImage object that represents the converted image.
     *
     * @see ImageReader
     * @see ConvertableImage
     */
    fun read(fileStream: Supplier<InputStream>,settings: List<Settings>): ConvertableImage

    /**
     * Determines whether the given media type is supported by this image reader.
     *
     * @param mediaType The media type to check. It should be in the format "type/subtype".
     * @return `true` if the media type is supported, `false` otherwise.
     */
    fun supports(mediaType: String): Boolean {
        val lowercase = mediaType.lowercase()
        return supportedTypes().any { lowercase.endsWith(it) }
    }

    /**
     * Retrieves the set of supported types by this image reader.
     *
     * @return A set of strings representing the supported media types. Each string should be in the format "type/subtype".
     */
    fun supportedTypes(): Set<String>
}
