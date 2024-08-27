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

package io.github.breninsul.simpleimageconvertor.service.transformer.predefined.filter

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.filter.AlphaMaskFilter
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter.AlphaMaskFilterSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.OperationWitSecondImageTransformer

/**
 * The `AlphaMaskFilterTransformer` class is an implementation of the
 * `OperationWitSecondImageTransformer` class. It represents a transformer
 * that performs the alpha mask filter operation on an image using a second
 * image as the mask.
 *
 * @param T The type of the operation settings. It must extend the
 *    `AlphaMaskFilterSettings` class.
 * @constructor Creates an `AlphaMaskFilterTransformer` instance.
 * @property name The name of the transformer which is "AlphaMaskFilter".
 * @see [link](https://sksamuel.github.io/scrimage/filters/)
 */
open class AlphaMaskFilterTransformer : OperationWitSecondImageTransformer<AlphaMaskFilterSettings>(AlphaMaskFilterSettings::class) {
    override val name: String = "AlphaMaskFilter"
    override fun mapOptionsToFrame(settings: AlphaMaskFilterSettings, frameImage: ImmutableImage): AlphaMaskFilterSettings {
        return AlphaMaskFilterSettings(settings.channel, ImageOrAnimation(null, frameImage))
    }

    override fun processTransformation(image: ImmutableImage, settings: AlphaMaskFilterSettings): ImmutableImage = image.filter(AlphaMaskFilter(settings.image.image ?: throw ImageTransformerException("Image should be static"), settings.channel))
}
