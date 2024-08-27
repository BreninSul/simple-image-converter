package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TransformSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer
import kotlin.reflect.KClass

/**
 * The `PredefinedTransformer` class is an abstract class that represents
 * a transformer for image processing operations. It provides a base
 * implementation for processing static images using a predefined set of
 * settings.
 *
 * @param T The type of the transform settings. It must implement the
 *    [TransformSettings] interface.
 * @property clazz The class of the transform settings type. It is used to
 *    identify and fetch the appropriate settings from the list of
 *    settings.
 */
abstract class PredefinedTransformer<T : TransformSettings>(protected open val clazz: KClass<T>) : ImageTransformer {


    override fun processStatic(image: ImmutableImage, settings: List<Settings>): ImmutableImage {
        val operationSettings = getOperationSettings(settings)
        return processTransformation(image, operationSettings)
    }

    /**
     * Returns the operation settings of the transformer.
     *
     * This method retrieves the operation settings from the list of settings
     * provided. It uses the class of the transform settings type specified in
     * the [PredefinedTransformer] class to identify and fetch the appropriate
     * settings. If no settings of the specified type are found in the list, it
     * throws an [ImageTransformerException].
     *
     * @param settings The list of settings to search for the operation
     *    settings. Each setting must implement the [Settings] interface.
     * @return The operation settings as an instance of the transform settings
     *    type [T].
     * @throws ImageTransformerException if no settings of type [T] are found
     *    in the list.
     */
    protected open fun getOperationSettings(settings: List<Settings>) = settings.firstOrNull { s -> clazz.isInstance(s) }?.let { it as T } ?: throw ImageTransformerException("No settings for $name")

    /**
     * Performs a transformation on an image using the provided settings.
     *
     * @param image The image to be processed. It must be an instance of
     *    ImmutableImage.
     * @param settings The settings to apply during the image processing. It
     *    must implement the TransformSettings interface.
     * @return The processed image as an instance of ImmutableImage.
     */
    protected abstract fun processTransformation(image: ImmutableImage, settings: T): ImmutableImage

    override fun List<Settings>.supports(): Boolean {
        return this.firstOrNull { s -> clazz.isInstance(s) } != null
    }

}
