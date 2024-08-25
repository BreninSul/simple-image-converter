package io.github.breninsul.simpleimageconvertor.dto.settings

interface WriterSettings: Settings {
    override fun getOrder(): Int =1
}