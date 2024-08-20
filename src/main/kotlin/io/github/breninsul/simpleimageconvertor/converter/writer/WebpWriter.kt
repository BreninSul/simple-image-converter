package io.github.breninsul.simpleimageconvertor.converter.writer

import com.sksamuel.scrimage.webp.Gif2WebpWriter
import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.ImageFormatEnum
import io.github.breninsul.simpleimageconvertor.dto.Settings
import io.github.breninsul.simpleimageconvertor.dto.WebpWriterSettings
import java.io.OutputStream
import java.util.function.Supplier

open class WebpWriter:ImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormatEnum.WEBP)

    override fun supportedTypes(): Set<ImageFormatEnum> {
        return supportedImageTypes
    }
    override fun write(image: ConvertableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        val webpSetting=settings.getSetting<WebpWriterSettings>()
        if (image.isAnimation()){
            val delegate=webpSetting?.let {Gif2WebpWriter(it.q,it.m,!it.lossless)}?:Gif2WebpWriter()
            out.get().use {  delegate.write(image.animation,it)}
        } else{
            val delegate=webpSetting?.let {com.sksamuel.scrimage.webp.WebpWriter(it.z ,it.q,it.m,it.lossless,it.noAlpha,it.multiThread)  }?:com.sksamuel.scrimage.webp.WebpWriter()
            out.get().use {  delegate.write(image.image,image.image!!.metadata,it)}
        }
    }

    override fun getOrder(): Int {
        return 1
    }

    override fun supportsAnimation(): Boolean =true
}