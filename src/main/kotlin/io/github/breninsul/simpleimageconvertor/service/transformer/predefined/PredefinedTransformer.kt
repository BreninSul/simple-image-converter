package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.TransformSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer
import kotlin.reflect.KClass

/**
 * The `PredefinedTransformer` class is an abstract class that represents a transformer for image processing operations.
 * It provides a base implementation for processing static images using a predefined set of settings.
 *
 * @param T The type of the transform settings. It must implement the [TransformSettings] interface.
 * @property clazz The class of the transform settings type. It is used to identify and fetch the appropriate settings from the list of settings.
 */
abstract class PredefinedTransformer<T: TransformSettings>(protected open val clazz: KClass<T>) : ImageTransformer {


    override fun processStatic(image:ImmutableImage,settings: List<Settings>): ImmutableImage {
        val operationSettings = settings.firstOrNull { s-> clazz.isInstance(s) }.let { it as T } ?: throw ImageTransformerException("No settings for $name")
        return processTransformation(image,operationSettings)
    }

    /**
     * Performs a transformation on an image using the provided settings.
     *
     * @param image The image to be processed. It must be an instance of ImmutableImage.
     * @param settings The settings to apply during the image processing. It must implement the TransformSettings interface.
     * @return The processed image as an instance of ImmutableImage.
     */
    protected abstract fun processTransformation(image:ImmutableImage,settings:T): ImmutableImage

    override fun List<Settings>.supports(): Boolean {
        return this.firstOrNull { s-> clazz.isInstance(s) } != null
    }

}
