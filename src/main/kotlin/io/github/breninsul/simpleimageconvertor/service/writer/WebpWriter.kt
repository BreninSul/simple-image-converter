package io.github.breninsul.simpleimageconvertor.service.writer

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.AnimatedGif
import com.sksamuel.scrimage.webp.Gif2WebpWriter
import io.github.breninsul.simpleimageconvertor.dto.*
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import io.github.breninsul.simpleimageconvertor.dto.settings.getSetting
import io.github.breninsul.simpleimageconvertor.dto.writer.WebpWriterSettings
import java.io.OutputStream
import java.util.function.Supplier

open class WebpWriter:AnimationImageWriter {
    protected open val supportedImageTypes = setOf(ImageFormat.WEBP)

    override fun supportedTypes(): Set<ImageFormat> {
        return supportedImageTypes
    }

    override fun writeAnimation(animation: AnimatedGif, settings: List<Settings>, out: Supplier<OutputStream>) {
        val webpSetting=settings.getSetting<WebpWriterSettings>()
        val delegate=webpSetting?.let {Gif2WebpWriter(it.q,it.m,!it.lossless)}?:Gif2WebpWriter()
        out.get().use {  delegate.write(animation,it)}
    }

    override fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        val webpSetting=settings.getSetting<WebpWriterSettings>()
        val delegate=webpSetting?.let {com.sksamuel.scrimage.webp.WebpWriter(it.z ,it.q,it.m,it.lossless,it.noAlpha,it.multiThread)  }?:com.sksamuel.scrimage.webp.WebpWriter()
        out.get().use {  delegate.write(image,image.metadata,it)}
    }

    override fun getOrder(): Int {
        return 1
    }

    override fun supportsAnimation(): Boolean =true
}