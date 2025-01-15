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
import io.github.breninsul.simpleimageconvertor.dto.settings.reader.ReaderSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TransformSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.writer.WriterSettings
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
     * Processes the input stream according to the provided settings and writes the result to the
     * output stream asynchronously.
     *
     * @param inputStream The input stream containing the data to be processed.
     * @param outputStream The output stream to which the processed data will be written.
     * @param writerSettings A list of settings to configure the writing process.
     * @param transformSettings A list of settings to configure transformations to be applied
     *                          during the process. Defaults to an empty list if not provided.
     * @param readerSettings A list of settings to configure the reading process. Defaults to an
     *                       empty list if not provided.
     * @param mimeType The MIME type of the data being processed. Nullable, default is null.
     * @param id An optional identifier for the process. Nullable, default is null.
     * @return A CompletableFuture containing the optional result identifier as a string.
     */
    fun processFuture(
        inputStream: InputStream,
        outputStream: OutputStream,
        writerSettings: List<WriterSettings>,
        transformSettings: List<TransformSettings> = listOf(),
        readerSettings: List<ReaderSettings> = listOf(),
        mimeType: String? = null,
        id: String? = null,
    ): CompletableFuture<String?> {
        return processFuture(inputStream, outputStream, readerSettings + writerSettings + transformSettings, mimeType, id)
    }

    /**
     * Processes the input stream according to the provided settings and writes the result to the
     * output stream asynchronously.
     *
     * @param inputStream The input stream containing the data to be processed.
     * @param outputStream The output stream where the processed data will be written.
     * @param settings A list of settings to configure the processing.
     * @param mimeType The MIME type of the data being processed. Nullable, defaults to null.
     * @param id An optional identifier for the process. Nullable, defaults to null.
     * @return A CompletableFuture containing the optional result identifier as a string.
     */
    fun processFuture(
        inputStream: InputStream,
        outputStream: OutputStream,
        settings: List<Settings>,
        mimeType: String? = null,
        id: String? = null,
    ): CompletableFuture<String?>

    /**
     * Processes the input stream according to the provided settings and writes the result to the output stream.
     *
     * @param inputStream The input stream containing data to be processed.
     * @param outputStream The output stream where the processed data will be written.
     * @param writerSettings A list of settings to configure the writing process.
     * @param transformSettings A list of settings to configure transformations during the process. Defaults to an empty list if not provided.
     * @param readerSettings A list of settings to configure the reading process. Defaults to an empty list if not provided.
     * @param mimeType The MIME type of the data being processed. Nullable, default is null.
     * @param id An optional identifier for the process. Nullable, default is null.
     * @return An optional result identifier as a string, or null if no identifier was generated.
     */
    fun process(
        inputStream: InputStream,
        outputStream: OutputStream,
        writerSettings: List<WriterSettings>,
        transformSettings: List<TransformSettings> = listOf(),
        readerSettings: List<ReaderSettings> = listOf(),
        mimeType: String? = null,
        id: String? = null,
    ): String? {
        return process(inputStream,outputStream, readerSettings + writerSettings + transformSettings, mimeType, id)
    }

    /**
     * Processes the input stream according to the provided settings and writes the result to the output stream.
     *
     * @param inputStream The input stream containing data to be processed.
     * @param outputStream The output stream where the processed data will be written.
     * @param settings A list of settings to configure the processing.
     * @param mimeType The MIME type of the data being processed. Nullable, defaults to null.
     * @param id An optional identifier for the process. Nullable, defaults to null.
     * @return An optional result identifier as a string, or null if no identifier was generated.
     */
    fun process(
        inputStream: InputStream,
        outputStream: OutputStream,
        settings: List<Settings>,
        mimeType: String? = null,
        id: String? = null,
    ): String?

    /**
     * Performs an image transformation based on the provided input stream and settings.
     *
     * @param inputStream The input stream containing the image data to be transformed.
     * @param transformSettings A list of settings to configure transformations to be applied during the process. Defaults to an empty list if not provided.
     * @param readerSettings A list of settings to configure the reading process. Defaults to an empty list if not provided.
     * @param mimeType The MIME type of the image data being processed. Nullable, defaults to null.
     * @param id An optional identifier for the transformation process. Nullable, defaults to null.
     * @return An ImageOrAnimation object representing the transformed image or animation.
     */
    fun performImageTransformation(
        inputStream: InputStream,
        transformSettings: List<TransformSettings> = listOf(),
        readerSettings: List<ReaderSettings> = listOf(),
        mimeType: String? = null,
        id: String? = null,
    ): ImageOrAnimation {
        return performImageTransformation(inputStream, readerSettings + transformSettings, mimeType, id)
    }

    /**
     * Performs an image transformation based on the provided input stream and settings.
     *
     * @param inputStream The input stream containing the image data to be transformed.
     * @param settings A list of settings that specify the details of the transformation process.
     * @param mimeType The MIME type of the image data being processed. Nullable, defaults to null.
     * @param id An optional identifier for the transformation process. Nullable, defaults to null.
     * @return An ImageOrAnimation object representing the transformed image or animation.
     */
    fun performImageTransformation(
        inputStream: InputStream,
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
