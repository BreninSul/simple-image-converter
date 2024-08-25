package io.github.breninsul.simpleimageconvertor.dto.reader

import io.github.breninsul.simpleimageconvertor.dto.settings.Settings

interface ReaderSettings: Settings {
    override fun getOrder(): Int =0
}