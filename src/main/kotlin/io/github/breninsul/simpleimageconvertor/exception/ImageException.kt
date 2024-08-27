package io.github.breninsul.simpleimageconvertor.exception

/**
 * Represents an exception that is thrown when an error occurs during image
 * processing or manipulation.
 *
 * @param name The name of the exception.
 * @param cause The underlying cause of the exception.
 */
open class ImageException(name: String? = null, cause: Throwable? = null) : RuntimeException(name, cause)