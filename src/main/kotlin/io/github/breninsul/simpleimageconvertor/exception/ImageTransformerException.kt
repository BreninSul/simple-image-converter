package io.github.breninsul.simpleimageconvertor.exception

/**
 * Represents an exception that is thrown when an error occurs during image
 * transformation.
 *
 * @param name The name of the exception.
 * @param cause The underlying cause of the exception.
 * @see ImageException
 */
open class ImageTransformerException(name: String? = null, cause: Throwable? = null) : ImageException(name, cause)