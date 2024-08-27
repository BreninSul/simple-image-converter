/*
 * MIT License
 * Copyright (c) 2024 BreninSul
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.breninsul.simpleimageconvertor.service.transformer.predefined.composite

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.composite.GenericBlenderCompositeDelegate
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.composite.BlenderCompositeSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.OperationWitSecondImageTransformer

/**
 * This class represents a composite transformer for blending images
 * together using the BlenderComposite algorithm. It extends the abstract
 * class OperationWitSecondImageTransformer and provides implementations
 * for processing the transformation.
 *
 * @constructor Creates a BlenderCompositeTransformer object.
 *
 *    @see [link](https://sksamuel.github.io/scrimage/transforms/)
 */
open class BlenderCompositeTransformer : OperationWitSecondImageTransformer<BlenderCompositeSettings>(BlenderCompositeSettings::class) {
    override val name: String = "BlenderComposite"
    override fun mapOptionsToFrame(settings: BlenderCompositeSettings, frameImage: ImmutableImage): BlenderCompositeSettings {
        return BlenderCompositeSettings(settings.alpha, settings.mode, ImageOrAnimation(null, frameImage))
    }

    override fun processTransformation(image: ImmutableImage, settings: BlenderCompositeSettings): ImmutableImage =
        image.composite(GenericBlenderCompositeDelegate(settings.alpha, settings.mode), settings.image.image ?: throw ImageTransformerException("Image should be static"))
}
