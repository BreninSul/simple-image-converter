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

package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.ScaleMethod
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.BoundTransformer

/**
 * The `BoundSettings` class represents a set of settings for performing
 * bound transformation on an image. It extends the `TransformSettings`
 * interface.
 *
 * @param resolution The resolution of the transformed image.
 * @param method The scale method to be used for the bound transformation.
 * @constructor Creates a `BoundSettings` instance with the specified
 *    resolution and scale method.
 * @property resolution The resolution of the transformed image.
 * @property method The scale method to be used for the bound
 *    transformation. It has a default value of `ScaleMethod.Bicubic`.
 * @see BoundSettings (https://sksamuel.github.io/scrimage/bound/)
 */
open class BoundSettings(
    open val resolution: Resolution,
    open val method: ScaleMethod = ScaleMethod.Bicubic,
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = BoundTransformer()
    }
}
