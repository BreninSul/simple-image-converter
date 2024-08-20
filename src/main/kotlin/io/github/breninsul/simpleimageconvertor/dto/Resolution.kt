package io.github.breninsul.simpleimageconvertor.dto

open class Resolution(
    open val width: Int,
    open val height: Int,
    open val keepAspectRater: Boolean = true,
) : Settings