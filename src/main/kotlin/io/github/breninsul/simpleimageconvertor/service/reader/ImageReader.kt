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

package io.github.breninsul.simpleimageconvertor.service.reader

import com.ashampoo.kim.Kim
import com.ashampoo.kim.format.ImageMetadata
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.Ordered
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.service.kim.BufferedInputStreamByteReader
import java.io.InputStream
import java.io.SequenceInputStream
import java.util.function.Supplier
import java.util.logging.Level
import java.util.logging.Logger

/**
 * The ImageReader interface provides a way to read and convert images from
 * various file types.
 */
interface ImageReader : Ordered {
    /**
     * Reads an image from a given file stream and applies the specified
     * settings to convert it into a ConvertableImage object.
     *
     * @param fileStream A Supplier of InputStream that represents the file
     *    stream from which the image will be read.
     * @param settings A List of Settings objects that specify the settings to
     *    be applied during the conversion of the image.
     * @return A ConvertableImage object that represents the converted image.
     * @see ImageReader
     * @see ImageOrAnimation
     */
    open fun read(fileStream: Supplier<InputStream>, settings: List<Settings>): ImageOrAnimation {
        fileStream.get().use {
            val (metadata, inputStream) = it.readMetadata()
            return readInternal(inputStream, settings, metadata)
        }
    }

    fun readInternal(fileStream: InputStream, settings: List<Settings>, metadata: ImageMetadata?): ImageOrAnimation

    /**
     * Determines whether the given media type is supported by this image
     * reader.
     *
     * @param mediaType The media type to check. It should be in the format
     *    "type/subtype".
     * @return `true` if the media type is supported, `false` otherwise.
     */
    fun supports(mediaType: String): Boolean {
        val lowercase = mediaType.lowercase()
        return supportedTypes().any { lowercase.endsWith(it) }
    }

    /**
     * Retrieves the set of supported types by this image reader.
     *
     * @return A set of strings representing the supported media types. Each
     *    string should be in the format "type/subtype".
     */
    fun supportedTypes(): Set<String>

    /**
     * Reads metadata from the current InputStream and returns a pair
     * containing the extracted metadata (if available) and a combined
     * InputStream.
     *
     * This method reads metadata from the InputStream using the Kim library,
     * clones the stream for further usage, and returns a pair of the metadata
     * and the combined stream. If an error occurs during metadata extraction,
     * the metadata component of the pair will be null.
     *
     * @return A Pair where the first element is the extracted ImageMetadata or
     *    null if an error occurred, and the second element is a combined
     *    InputStream for further processing.
     */
    fun InputStream.readMetadata(): Pair<ImageMetadata?, InputStream> {
        val byteReader = BufferedInputStreamByteReader(this,false)
        val metadata = try {
            Kim.readMetadata(byteReader)
        } catch (e: Exception) {
            logger.log(Level.FINE, "Error while read metadata ${e.javaClass}:${e.message}")
            null
        }
        byteReader.readOutputStream.flush()
        val clonedStream = byteReader.readOutputStream.toByteArray().inputStream()
        val combinedStream = SequenceInputStream(clonedStream, this)
        return metadata to combinedStream
    }

    companion object {
        private val logger = Logger.getLogger(this::class.java.name)
    }
}
