/*
 * MIT License
 * Copyright (c) 2024 BreninSul
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.breninsul.simpleimageconvertor.service.processor

import io.github.breninsul.simpleimageconvertor.dto.*
import io.github.breninsul.simpleimageconvertor.dto.reader.ReaderSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TransformSettings
import io.github.breninsul.simpleimageconvertor.dto.writer.WriterSettings
import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

/**
 * The ImageProcessorService interface provides methods for asynchronously
 * processing images and performing image transformations. It offers
 * flexibility to apply various settings and transformers during the image
 * processing.
 *
 * @see DefaultImageProcessorService
 */
interface ImageProcessorService {
    /**
     * Asynchronously processes an image and returns a CompletableFuture with
     * the result.
     *
     * @param inputStreamSupplier A supplier that provides the input stream for
     *    the image.
     * @param outputStreamSupplier A supplier that provides the output stream
     *    for the processed image.
     * @param writerSettings A list of writer settings to be applied during the
     *    image processing.
     * @param transformSettings A list of transform settings to be applied
     *    during the image processing. Defaults to an empty list.
     * @param readerSettings A list of reader settings to be applied during the
     *    image processing. Defaults to an empty list.
     * @param mimeType Image mime type. Will be resolved automatically if not
     *    provided.
     * @param id An optional identifier for the image processing operation.
     * @return A CompletableFuture that represents the asynchronous operation
     *    and contains the id.
     */
    fun processFuture(
        inputStreamSupplier: Supplier<InputStream>,
        outputStreamSupplier: Supplier<OutputStream>,
        writerSettings: List<WriterSettings>,
        transformSettings: List<TransformSettings> = listOf(),
        readerSettings: List<ReaderSettings> = listOf(),
        mimeType: String? = null,
        id: String? = null,
    ): CompletableFuture<String?> {
        return processFuture(inputStreamSupplier, outputStreamSupplier, readerSettings + writerSettings + transformSettings, mimeType, id)
    }

    /**
     * Asynchronously processes an image and returns a CompletableFuture with
     * the result.
     *
     * @param inputStreamSupplier A supplier that provides the input stream for
     *    the image.
     * @param outputStreamSupplier A supplier that provides the output stream
     *    for the processed image.
     * @param settings A list of settings to be applied during the image
     *    processing.
     * @param mimeType Image mime type. Will be resolved automatically if not
     *    provided
     * @param id An optional identifier for the image processing operation.
     * @return A CompletableFuture that represents the asynchronous operation
     *    and contains the id.
     */
    fun processFuture(
        inputStreamSupplier: Supplier<InputStream>,
        outputStreamSupplier: Supplier<OutputStream>,
        settings: List<Settings>,
        mimeType: String? = null,
        id: String? = null,
    ): CompletableFuture<String?>

    /**
     * Asynchronously processes an image and returns the result as a String.
     *
     * @param inputStreamSupplier A supplier that provides the input stream for
     *    the image.
     * @param outputStreamSupplier A supplier that provides the output stream
     *    for the processed image.
     * @param writerSettings A list of writer settings to be applied during the
     *    image processing.
     * @param transformSettings A list of transform settings to be applied
     *    during the image processing. Defaults to an empty list.
     * @param readerSettings A list of reader settings to be applied during the
     *    image processing. Defaults to an empty list.
     * @param mimeType Image mime type. Will be resolved automatically if not
     *    provided.
     * @param id An optional identifier for the image processing operation.
     * @return The result of the image processing as a String. Returns null if
     *    an error occurs.
     */
    fun process(
        inputStreamSupplier: Supplier<InputStream>,
        outputStreamSupplier: Supplier<OutputStream>,
        writerSettings: List<WriterSettings>,
        transformSettings: List<TransformSettings> = listOf(),
        readerSettings: List<ReaderSettings> = listOf(),
        mimeType: String? = null,
        id: String? = null,
    ): String? {
        return process(inputStreamSupplier, outputStreamSupplier, readerSettings + writerSettings + transformSettings, mimeType, id)
    }

    /**
     * Processes an image and returns a CompletableFuture with the result.
     *
     * @param inputStreamSupplier A supplier that provides the input stream for
     *    the image.
     * @param outputStreamSupplier A supplier that provides the output stream
     *    for the processed image.
     * @param settings A list of settings to be applied during the image
     *    processing.
     * @param mimeType Image mime type. Will be resolved automatically if not
     *    provided
     * @param id An optional identifier for the image processing operation.
     * @return id.
     */
    fun process(
        inputStreamSupplier: Supplier<InputStream>,
        outputStreamSupplier: Supplier<OutputStream>,
        settings: List<Settings>,
        mimeType: String? = null,
        id: String? = null,
    ): String?

    /**
     * Performs an image transformation based on the given parameters.
     *
     * @param inputStreamSupplier A supplier that provides the input stream for
     *    the image.
     * @param transformSettings A list of transform settings to be applied
     *    during the image processing. Defaults to an empty list.
     * @param readerSettings A list of reader settings to be applied during the
     *    image processing. Defaults to an empty list.
     * @param mimeType Image mime type. Will be resolved automatically if not
     *    provided.
     * @param id An optional identifier for the image processing operation.
     * @return The transformed image as a ConvertableImage object of type
     *    ImageOrAnimation.
     */
    fun performImageTransformation(
        inputStreamSupplier: Supplier<InputStream>,
        transformSettings: List<TransformSettings> = listOf(),
        readerSettings: List<ReaderSettings> = listOf(),
        mimeType: String? = null,
        id: String? = null,
    ): ImageOrAnimation {
        return performImageTransformation(inputStreamSupplier, readerSettings + transformSettings, mimeType, id)
    }

    /**
     * Performs an image transformation based on the given parameters.
     *
     * @param inputStreamSupplier A supplier that provides the input stream for
     *    the image.
     * @param mimeType Image mime type. Will be resolved automatically if not
     *    provided.
     * @param id An optional identifier for the image processing operation.
     * @param transformSettings A list of transform settings to be applied
     *    during the image processing. Defaults to an empty list.
     * @param readerSettings A list of reader settings to be applied during the
     *    image processing. Defaults to an empty list.
     * @return The transformed image as a ConvertableImage object of type
     *    ImageOrAnimation.
     */
    fun performImageTransformation(
        inputStreamSupplier: Supplier<InputStream>,
        settings: List<Settings>,
        mimeType: String? = null,
        id: String? = null,
    ): ImageOrAnimation

    /**
     * The `Default` class is a singleton object that extends the
     * `DefaultImageProcessorService` class. It provides default
     * implementations for image processing methods.
     *
     * @see DefaultImageProcessorService
     */
    object Default : DefaultImageProcessorService()
}
