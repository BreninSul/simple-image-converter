package io.github.breninsul.simpleimageconvertor.service.transformer

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.ScaleMethod
import io.github.breninsul.simpleimageconvertor.dto.Resolution
import io.github.breninsul.simpleimageconvertor.dto.ScaleSettings
import io.github.breninsul.simpleimageconvertor.dto.Settings
import io.github.breninsul.simpleimageconvertor.dto.getSetting
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import java.util.logging.Logger

open class ScaleTransformer : ImageTransformer {

    override val name: String="Scale"

    override fun processStatic(image:ImmutableImage,settings: List<Settings>): ImmutableImage {
        val scaleSettings = settings.getSetting<ScaleSettings>() ?: throw ImageTransformerException("No settings for scale")
        return scale(image,scaleSettings.resolution, scaleSettings.method)
    }

    protected open fun scale(image:ImmutableImage,resolution: Resolution, method: ScaleMethod): ImmutableImage = image.scaleTo(resolution.width, resolution.height, method)

    override fun List<Settings>.supports(): Boolean {
        return this.getSetting<ScaleSettings>() != null
    }

    companion object {
        val logger = Logger.getLogger(this::class.java.name)
    }
}
