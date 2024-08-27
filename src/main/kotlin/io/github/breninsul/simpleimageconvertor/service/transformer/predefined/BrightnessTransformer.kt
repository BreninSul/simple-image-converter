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
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.BrightnessSettings

/**
 * The `BrightnessTransformer` class is a concrete implementation of the
 * abstract class `PredefinedTransformer`. It represents a transformer
 * that adjusts the brightness of an image using the provided settings.
 *
 * @param T The type of the transform settings. It must be a subclass of
 *    `BrightnessSettings`.
 * @constructor Creates a `BrightnessTransformer` instance.
 * @property name The name of the brightness transformer.
 * @see PredefinedTransformer
 * @see BrightnessSettings
 * @see [link](https://sksamuel.github.io/scrimage/brightness/)
 */
open class BrightnessTransformer : PredefinedTransformer<BrightnessSettings>(BrightnessSettings::class) {
    override val name: String = "Brightness"

    override fun processTransformation(image: ImmutableImage, settings: BrightnessSettings): ImmutableImage = image.brightness(settings.factor)
}
