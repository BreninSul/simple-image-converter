package io.github.breninsul.simpleimageconvertor.service.processor

import io.github.breninsul.simpleimageconvertor.dto.*
import io.github.breninsul.simpleimageconvertor.dto.reader.ReaderSettings
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer
import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

/**
 * The ImageProcessorService interface provides methods for asynchronously processing images and performing image transformations.
 * It offers flexibility to apply various settings and transformers during the image processing.
 *
 * @see DefaultImageProcessorService
 */
interface ImageProcessorService {
    /**
     * Asynchronously processes an image and returns a CompletableFuture with the result.
     *
     * @param inputStreamSupplier A supplier that provides the input stream for the image.
     * @param outputStreamSupplier A supplier that provides the output stream for the processed image.
     * @param writerSettings A list of writer settings to be applied during the image processing.
     * @param transformers A list of image transformers to be applied during the image processing. Defaults to an empty list.
     * @param transformSettings A list of transform settings to be applied during the image processing. Defaults to an empty list.
     * @param readerSettings A list of reader settings to be applied during the image processing. Defaults to an empty list.
     * @param id An optional identifier for the image processing operation.
     * @return A CompletableFuture that represents the asynchronous operation and contains the id .
     */
    fun processFuture(
        inputStreamSupplier: Supplier<InputStream>,
        outputStreamSupplier: Supplier<OutputStream>,
        writerSettings: List<WriterSettings>,
        transformers: List<ImageTransformer> = listOf(),
        transformSettings: List<TransformSettings> = listOf(),
        readerSettings: List<ReaderSettings> = listOf(),
        id: String? = null,
    ): CompletableFuture<String?>{
        return processFuture(inputStreamSupplier, outputStreamSupplier, readerSettings + writerSettings + transformSettings, transformers, id)
    }

    /**
     * Asynchronously processes an image and returns a CompletableFuture with the result.
     *
     * @param inputStreamSupplier A supplier that provides the input stream for the image.
     * @param outputStreamSupplier A supplier that provides the output stream for the processed image.
     * @param settings A list of settings to be applied during the image processing.
     * @param transformers A list of image transformers to be applied during the image processing.
     * @param id An optional identifier for the image processing operation.
     * @return A CompletableFuture that represents the asynchronous operation and contains the id.
     */
    fun processFuture(
        inputStreamSupplier: Supplier<InputStream>,
        outputStreamSupplier: Supplier<OutputStream>,
        settings: List<Settings>,
        transformers: List<ImageTransformer>,
        id: String? = null,
    ): CompletableFuture<String?>

    /**
     * Processes an image and returns a CompletableFuture with the result.
     *
     * @param inputStreamSupplier A supplier that provides the input stream for the image.
     * @param outputStreamSupplier A supplier that provides the output stream for the processed image.
     * @param writerSettings A list of writer settings to be applied during the image processing.
     * @param transformers A list of image transformers to be applied during the image processing. Defaults to an empty list.
     * @param transformSettings A list of transform settings to be applied during the image processing. Defaults to an empty list.
     * @param readerSettings A list of reader settings to be applied during the image processing. Defaults to an empty list.
     * @param id An optional identifier for the image processing operation.
     * @return id .
     */
    fun process(
        inputStreamSupplier: Supplier<InputStream>,
        outputStreamSupplier: Supplier<OutputStream>,
        writerSettings: List<WriterSettings>,
        transformers: List<ImageTransformer> = listOf(),
        transformSettings: List<TransformSettings> = listOf(),
        readerSettings: List<ReaderSettings> = listOf(),
        id: String? = null,
    ): String? {
        return process(inputStreamSupplier, outputStreamSupplier, readerSettings + writerSettings + transformSettings, transformers, id)
    }
    /**
     * Processes an image and returns a CompletableFuture with the result.
     *
     * @param inputStreamSupplier A supplier that provides the input stream for the image.
     * @param outputStreamSupplier A supplier that provides the output stream for the processed image.
     * @param settings A list of settings to be applied during the image processing.
     * @param transformers A list of image transformers to be applied during the image processing.
     * @param id An optional identifier for the image processing operation.
     * @return id.
     */
    fun process(
        inputStreamSupplier: Supplier<InputStream>,
        outputStreamSupplier: Supplier<OutputStream>,
        settings: List<Settings>,
        transformers: List<ImageTransformer>,
        id: String? = null,
    ): String?

    /**
     * Performs an image transformation based on the given parameters.
     *
     * @param inputStreamSupplier A supplier that provides the input stream for the image.
     * @param transformers A list of image transformers to be applied during the image processing. Defaults to an empty list.
     * @param transformSettings A list of transform settings to be applied during the image processing. Defaults to an empty list.
     * @param readerSettings A list of reader settings to be applied during the image processing. Defaults to an empty list.
     * @param id An optional identifier for the image processing operation.
     * @return The transformed image as a ConvertableImage object.
     */
    fun performImageTransformation(
        inputStreamSupplier: Supplier<InputStream>,
        transformers: List<ImageTransformer> = listOf(),
        transformSettings: List<TransformSettings> = listOf(),
        readerSettings: List<ReaderSettings> = listOf(),
        id: String? = null,
    ): ConvertableImage {
        return performImageTransformation(inputStreamSupplier, transformers, readerSettings + transformSettings, id)
    }

    /**
     * Performs an image transformation based on the given parameters.
     *
     * @param inputStreamSupplier A supplier that provides the input stream for the image.
     * @param transformers A list of image transformers to be applied during the image processing. Defaults to an empty list.
     * @param transformSettings A list of transform settings to be applied during the image processing. Defaults to an empty list.
     * @param readerSettings A list of reader settings to be applied during the image processing. Defaults to an empty list.
     * @param id An optional identifier for the image processing operation.
     * @return The transformed image as a ConvertableImage object.
     */
    fun performImageTransformation(
        inputStreamSupplier: Supplier<InputStream>,
        transformers: List<ImageTransformer>,
        settings: List<Settings>,
        id: String? = null,
    ): ConvertableImage

    /**
     * The `Default` class is a singleton object that extends the `DefaultImageProcessorService` class.
     * It provides default implementations for image processing methods.
     *
     * @see DefaultImageProcessorService
     */
    object Default:DefaultImageProcessorService()
}
