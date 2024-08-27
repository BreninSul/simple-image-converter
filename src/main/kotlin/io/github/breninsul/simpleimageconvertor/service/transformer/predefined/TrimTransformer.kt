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

package io.github.breninsul.simpleimageconvertor.service.transformer.predefined

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TrimSettings

/**
 * The `TrimTransformer` class is a subclass of `PredefinedTransformer`
 * that represents a transformer for trimming images. It overrides the
 * `processTransformation` method to perform the trimming operation on the
 * image.
 *
 * @constructor Creates a new `TrimTransformer` object.
 * @property name The name of the trim transformer, which is set to "Trim".
 * @see [link](https://sksamuel.github.io/scrimage/trim/)
 */
open class TrimTransformer : PredefinedTransformer<TrimSettings>(TrimSettings::class) {
    override val name: String = "Trim"

    override fun processTransformation(image: ImmutableImage, settings: TrimSettings): ImmutableImage =
        image.trim(settings.left, settings.top, settings.right, settings.bottom)
}
