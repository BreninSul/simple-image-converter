package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.Position
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.CoverTransformer

open class CoverSettings(
    open val resolution: Resolution,
    open val position: Position = Position.TopLeft,
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = CoverTransformer()
    }
}