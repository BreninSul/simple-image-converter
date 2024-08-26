package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.filter.SaltAndPepperFilter

open class SaltAndPepperFilterSettings(
    open val salt: Double = 0.0,
    open val pepper: Double = 0.0,

    ) : CommonTransformSettings {
    override fun toFilter() = SaltAndPepperFilter(salt, pepper)
    override fun transformerName(): String = "SaltAndPepperFilter"
}