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

import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TransformSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageException
import io.github.breninsul.simpleimageconvertor.service.consumer.DefaultImageConsumer
import io.github.breninsul.simpleimageconvertor.service.consumer.ImageConsumer
import io.github.breninsul.simpleimageconvertor.service.convert.DefaultImageConverter
import io.github.breninsul.simpleimageconvertor.service.convert.ImageConverter
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer
import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.function.Supplier
import java.util.logging.Level
import java.util.logging.Logger

open class DefaultImageProcessorService(
    protected open val consumer: ImageConsumer = DefaultImageConsumer(),
    protected open val converter: ImageConverter = DefaultImageConverter(),
    protected open val executorService: Executor? = null,
    protected open val loggingLevel: Level = Level.INFO,
) : ImageProcessorService {

    override fun processFuture(
        inputStreamSupplier: Supplier<InputStream>,
        outputStreamSupplier: Supplier<OutputStream>,
        settings: List<Settings>,
        mimeType: String?,
        id: String?,
    ): CompletableFuture<String?> {
        if (executorService == null) {
            throw ImageException("Executor is not set")
        }
        converter.checkSettings(settings)
        val result =
            CompletableFuture.supplyAsync(
                { process(inputStreamSupplier, outputStreamSupplier, settings, mimeType, id) },
                executorService,
            )
        return result
    }


    override fun process(
        inputStreamSupplier: Supplier<InputStream>,
        outputStreamSupplier: Supplier<OutputStream>,
        settings: List<Settings>,
        mimeType: String?,
        id: String?,
    ): String? {
        try {
            val time = System.currentTimeMillis()
            val processed = performImageTransformation(inputStreamSupplier, settings, mimeType, id)
            val afterProcessTime = System.currentTimeMillis()
            converter.convert(processed, settings, outputStreamSupplier)
            logger.log(loggingLevel, "Image write $id took ${System.currentTimeMillis() - afterProcessTime} ms. Total time ${System.currentTimeMillis() - time} ms")
        } catch (t: Throwable) {
            throw if (t is ImageException) t else ImageException(t.message, t)
        }
        return id
    }


    override fun performImageTransformation(
        inputStreamSupplier: Supplier<InputStream>,
        settings: List<Settings>,
        mimeType: String?,
        id: String?,
    ): ImageOrAnimation {
        val transformers: List<ImageTransformer> = settings.filterIsInstance<TransformSettings>().map { it.createTransformer() }
        try {
            val time = System.currentTimeMillis()
            val image = consumer.read(inputStreamSupplier, settings, mimeType)
            val afterReadTime = System.currentTimeMillis()
            logger.log(loggingLevel, "Image read $id took ${System.currentTimeMillis() - time} ms")
            val processed = transformers.fold(image) { img, transformer ->
                val processStartTime = System.currentTimeMillis()
                val result = transformer.process(img, settings)
                logger.log(loggingLevel, "Image transform ${transformer.name} $id took ${System.currentTimeMillis() - processStartTime} ms")
                return@fold result
            }
            logger.log(loggingLevel, "Image transformers all $id took ${System.currentTimeMillis() - afterReadTime} ms")
            return processed
        } catch (t: Throwable) {
            throw if (t is ImageException) t else ImageException(t.message, t)
        }
    }

    companion object {
        val logger = Logger.getLogger(this::class.java.name)
    }
}
