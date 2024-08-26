package io.github.breninsul.simpleimageconvertor.exception

/**
 * An exception that is thrown when an error occurs during image converting.
 *
 * @param name the name of the exception
 * @param cause the underlying cause of the exception
 */
open class ImageConvertingException(name:String?=null, cause:Throwable?=null) :ImageException(name,cause)