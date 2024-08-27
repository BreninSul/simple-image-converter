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
import com.sksamuel.scrimage.filter.ErrorSpotterFilter
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter.ErrorSpotterFilterSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.OperationWitSecondImageTransformer

/**
 * This class represents an implementation of the
 * OperationWitSecondImageTransformer abstract class. It performs image
 * processing operations using the ErrorSpotterFilter. It provides methods
 * to map options to a frame, process the transformation, and resolve
 * destination frames.
 *
 * @constructor Creates an ErrorSpotterFilterTransformer instance.
 * @see [link](https://sksamuel.github.io/scrimage/filters/)
 */
open class ErrorSpotterFilterTransformer : OperationWitSecondImageTransformer<ErrorSpotterFilterSettings>(ErrorSpotterFilterSettings::class) {
    override val name: String = "ErrorSpotterFilter"
    override fun mapOptionsToFrame(settings: ErrorSpotterFilterSettings, frameImage: ImmutableImage): ErrorSpotterFilterSettings {
        return ErrorSpotterFilterSettings(settings.ratio, ImageOrAnimation(null, frameImage))
    }

    override fun processTransformation(image: ImmutableImage, settings: ErrorSpotterFilterSettings): ImmutableImage = image.filter(ErrorSpotterFilter(settings.image.image ?: throw ImageTransformerException("Image should be static"), settings.ratio))
}
