package io.github.breninsul.simpleimageconvertor.service.transformer.predefined.composite

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.composite.GenericBlenderCompositeDelegate
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.composite.BlenderCompositeSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.OperationWitSecondImageTransformer

open class BlenderCompositeTransformer : OperationWitSecondImageTransformer<BlenderCompositeSettings>(BlenderCompositeSettings::class) {
    override val name: String="BlenderComposite"
    override fun mapOptionsToFrame(settings: BlenderCompositeSettings, frameImage: ImmutableImage): BlenderCompositeSettings {
      return  BlenderCompositeSettings(settings.alpha,settings.mode, ImageOrAnimation(null, frameImage))
    }

    override fun processTransformation(image: ImmutableImage, settings: BlenderCompositeSettings): ImmutableImage = image.composite(GenericBlenderCompositeDelegate(settings.alpha,settings.mode),settings.image.image?: throw ImageTransformerException("Image should be static"))
}
