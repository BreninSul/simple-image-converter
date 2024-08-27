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
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TakeSettings

/**
 * The `TakeTransformer` class is an implementation of the
 * `PredefinedTransformer` abstract class. It represents a transformer that
 * applies cropping operations to an image based on predefined settings.
 *
 * @constructor Creates a new instance of the `TakeTransformer` class.
 * @property name The name of the transformer.
 * @see [link](https://sksamuel.github.io/scrimage/take/)
 */
open class TakeTransformer : PredefinedTransformer<TakeSettings>(TakeSettings::class) {
    override val name: String = "Take"

    override fun processTransformation(image: ImmutableImage, settings: TakeSettings): ImmutableImage =
        when (settings.type) {
            TakeSettings.Type.LEFT -> image.takeLeft(settings.value)
            TakeSettings.Type.RIGHT -> image.takeRight(settings.value)
            TakeSettings.Type.TOP -> image.takeTop(settings.value)
            TakeSettings.Type.BOTTOM -> image.takeBottom(settings.value)
        }
}
