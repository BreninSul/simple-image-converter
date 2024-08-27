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
import com.sksamuel.scrimage.filter.Filter
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter.CommonTransformSettings
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.PredefinedTransformer

/**
 * The `GeneralFilterTransformer` class is an open class that represents a
 * transformer for applying a filter to an image. It extends the abstract
 * class `PredefinedTransformer` and implements the `ImageTransformer`
 * interface.
 *
 * @param name The name of the transformer.
 * @param filter The filter to apply to the image.
 * @see [link](https://sksamuel.github.io/scrimage/filters/)
 */
open class GeneralFilterTransformer(
    override val name: String,
    protected open val filter: Filter,
) : PredefinedTransformer<CommonTransformSettings>(CommonTransformSettings::class) {

    override fun processTransformation(image: ImmutableImage, settings: CommonTransformSettings): ImmutableImage = image.filter(filter)
}
