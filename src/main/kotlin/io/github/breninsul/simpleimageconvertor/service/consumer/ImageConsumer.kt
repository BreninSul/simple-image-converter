package io.github.breninsul.simpleimageconvertor.service.consumer

import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import java.io.InputStream
import java.util.function.Supplier

/**
 * Represents a consumer of images that can read an image from an input stream using specified settings.
 */
interface ImageConsumer {
    /**
     * Reads an image from an input stream using the specified settings.
     *
     * @param inputStreamSupplier The supplier of the input stream from which the image will be read.
     * @param settings The list of settings to be applied while reading the image.
     * @return The convertable image that has been read.
     * @throws ImageException If an error occurs while reading the image.
     */
    fun read(
        inputStreamSupplier: Supplier<InputStream>,
        settings: List<Settings>,
        mimeType: String? = null,
    ): ImageOrAnimation
}
