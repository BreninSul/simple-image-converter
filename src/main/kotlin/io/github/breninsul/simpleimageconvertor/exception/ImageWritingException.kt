package io.github.breninsul.simpleimageconvertor.exception

/**
 * Represents an exception that is thrown when an error occurs during image
 * writing.
 *
 * @param name The name of the exception.
 * @param cause The underlying cause of the exception.
 */
open class ImageWritingException(name: String? = null, cause: Throwable? = null) : ImageException(name, cause)