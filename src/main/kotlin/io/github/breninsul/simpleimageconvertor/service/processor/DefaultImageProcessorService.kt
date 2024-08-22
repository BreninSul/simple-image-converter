package io.github.breninsul.simpleimageconvertor.service.processor

import io.github.breninsul.simpleimageconvertor.dto.*
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
): ImageProcessorService {

    override fun processFuture(
        inputStreamSupplier: Supplier<InputStream>,
        outputStreamSupplier: Supplier<OutputStream>,
        settings: List<Settings>,
        transformers: List<ImageTransformer>,
        mimeType: String?,
        id: String?,
    ): CompletableFuture<String?> {
        if (executorService == null) {
            throw ImageException("Executor is not set")
        }
        converter.checkSettings(settings)
        val result =
            CompletableFuture.supplyAsync(
                { process(inputStreamSupplier, outputStreamSupplier, settings, transformers,mimeType, id) },
                executorService,
            )
        return result
    }


    override fun process(
        inputStreamSupplier: Supplier<InputStream>,
        outputStreamSupplier: Supplier<OutputStream>,
        settings: List<Settings>,
        transformers: List<ImageTransformer>,
        mimeType: String?,
        id: String?,
    ): String? {
        try {
            val time = System.currentTimeMillis()
            val processed = performImageTransformation(inputStreamSupplier, transformers, settings,mimeType, id)
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
        transformers: List<ImageTransformer>,
        settings: List<Settings>,
        mimeType: String?,
        id: String?,
    ): ImageOrAnimation {
        try {
            val time = System.currentTimeMillis()
            val image = consumer.read(inputStreamSupplier, settings,mimeType)
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
