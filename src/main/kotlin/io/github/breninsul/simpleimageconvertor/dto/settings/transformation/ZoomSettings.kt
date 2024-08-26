package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.ScaleMethod
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ScaleTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ZoomTransformer

open class ZoomSettings(
    open val scaleFactor: Double = 0.0,
    open val scaleMethod: ScaleMethod = ScaleMethod.Bicubic,
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = ZoomTransformer()
    }
}