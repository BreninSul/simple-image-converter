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
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.AutocropSettings

/**
 * The `AutocropTransformer` class is a subclass of the
 * `PredefinedTransformer` class that represents a transformer for
 * autocropping images. It provides functionality to autocrop an image
 * based on the specified settings.
 *
 * @constructor Creates an instance of the `AutocropTransformer` class.
 * @property name The name of the transformer, which is set to "Autocrop".
 * @see [link](https://sksamuel.github.io/scrimage/autocrop/)
 */
open class AutocropTransformer : PredefinedTransformer<AutocropSettings>(AutocropSettings::class) {
    override val name: String = "Autocrop"

    override fun processTransformation(image: ImmutableImage, settings: AutocropSettings): ImmutableImage = image.autocrop(settings.backgroundColour, settings.colourTolerance)
}
