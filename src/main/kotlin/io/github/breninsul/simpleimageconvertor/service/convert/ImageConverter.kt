package io.github.breninsul.simpleimageconvertor.service.convert

import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.Settings
import java.io.OutputStream
import java.util.function.Supplier

/**
 * Interface for converting an image using specified settings and writing the output to a given output stream.
 */
interface ImageConverter {
    /**
     * Converts the given [image] with the specified [settings] and writes the output to the [outputSupplier].
     *
     * @param image The image to be converted. It must be an instance of [ConvertableImage].
     * @param settings The list of settings to be applied during the conversion. Each setting must implement the [Settings] interface.
     * @param outputSupplier The supplier of the output stream where the converted image will be written to.
     */
    fun convert(
        image: ConvertableImage,
        settings: List<Settings>,
        outputSupplier: Supplier<OutputStream>
    )

    /**
     * Checks the validity of the provided settings.
     *
     * @param settings The list of settings to be checked. Each setting must implement the [Settings] interface.
     */
    fun checkSettings(settings: List<Settings>)
}