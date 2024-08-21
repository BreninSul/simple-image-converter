package io.github.breninsul.simpleimageconvertor.dto

interface WriterSettings:Settings {
    override fun getOrder(): Int =1
}