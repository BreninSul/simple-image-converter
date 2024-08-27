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
import com.sksamuel.scrimage.composite.AlphaComposite
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.composite.AlphaCompositeSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.OperationWitSecondImageTransformer

/**
 * This class represents a transformer that applies the alpha composite
 * operation on images. It extends the `OperationWitSecondImageTransformer`
 * class and provides implementations for mapping options to frames and
 * performing the transformation.
 *
 * @constructor Creates an instance of the `AlphaCompositeTransformer`
 *    class.
 * @property name The name of the transformer, which is "AlphaComposite".
 * @see [link](https://sksamuel.github.io/scrimage/transforms/)
 */
open class AlphaCompositeTransformer : OperationWitSecondImageTransformer<AlphaCompositeSettings>(AlphaCompositeSettings::class) {
    override val name: String = "AlphaComposite"

    override fun mapOptionsToFrame(settings: AlphaCompositeSettings, frameImage: ImmutableImage): AlphaCompositeSettings {
        return AlphaCompositeSettings(settings.alpha, ImageOrAnimation(null, frameImage))
    }

    override fun processTransformation(image: ImmutableImage, settings: AlphaCompositeSettings): ImmutableImage = image.composite(AlphaComposite(settings.alpha), settings.image.image ?: throw ImageTransformerException("Image should be static"))
}
