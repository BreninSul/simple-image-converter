package io.github.breninsul.simpleimageconvertor.service.writer

import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.Ordered
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import java.io.OutputStream
import java.util.function.Supplier

/** An interface for writing images in various formats. */
interface ImageWriter : Ordered {
    /**
     * Checks if the specified media type is supported by this image writer.
     *
     * @param mediaType the media type to check
     * @return `true` if the media type is supported, `false` otherwise
     */
    fun supports(mediaType: ImageFormat): Boolean {
        return supportedTypes().any { mediaType.equals(it) }
    }

    /**
     * Returns the set of supported image formats by this image writer.
     *
     * @return the set of supported image formats
     */
    fun supportedTypes(): Set<ImageFormat>

    /**
     * Writes the given ConvertableImage using the specified Settings and
     * outputs the result to the provided OutputStream.
     *
     * @param image the ConvertableImage to write
     * @param settings the list of Settings to apply during the writing process
     * @param out the Supplier of OutputStream to write the image to
     */
    fun write(image: ImageOrAnimation, settings: List<Settings>, out: Supplier<OutputStream>)

    /**
     * Checks if the image writer supports animation.
     *
     * @return `true` if the image writer supports animation, `false` otherwise
     */
    fun supportsAnimation(): Boolean = false


}

