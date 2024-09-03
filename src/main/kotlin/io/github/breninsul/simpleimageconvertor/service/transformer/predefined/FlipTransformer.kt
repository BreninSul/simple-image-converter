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
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.FlipSettings

/**
 * The `FlipTransformer` class is an open class that represents
 * a transformer for flipping images. It extends the
 * `PredefinedTransformer` class and overrides the required methods.
 *
 * @constructor Creates a new `FlipTransformer` instance.
 * @see [link](https://sksamuel.github.io/scrimage/flip/)
 */
open class FlipTransformer : PredefinedTransformer<FlipSettings>(FlipSettings::class) {
    override val name: String = "Flip"

    override fun processTransformation(image: ImmutableImage, settings: FlipSettings): ImmutableImage = when (settings.type) {
        FlipSettings.Type.HORIZONTAL -> image.flipX()
        FlipSettings.Type.VERTICAL -> image.flipY()
    }
}
