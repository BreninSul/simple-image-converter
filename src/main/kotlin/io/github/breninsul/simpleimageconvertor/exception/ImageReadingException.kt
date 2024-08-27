package io.github.breninsul.simpleimageconvertor.exception

/**
 * Represents an exception that is thrown when an error occurs during image
 * reading.
 *
 * @param name The name of the exception.
 * @param cause The underlying cause of the exception.
 */
open class ImageReadingException(name: String? = null, cause: Throwable? = null) : ImageException(name, cause)