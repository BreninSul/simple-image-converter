package io.github.breninsul.simpleimageconvertor.dto.writer

import io.github.breninsul.simpleimageconvertor.dto.settings.WriterSettings
import javax.imageio.ImageWriteParam

open class ImageIOWriterSettings: WriterSettings, ImageWriteParam() {

}