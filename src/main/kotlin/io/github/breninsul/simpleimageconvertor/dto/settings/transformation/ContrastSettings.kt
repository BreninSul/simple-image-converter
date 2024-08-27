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

import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ContrastTransformer

/**
 * The `ContrastSettings` class represents settings for applying contrast
 * adjustments to images. It is an open class that implements the
 * `TransformSettings` interface.
 *
 * @param factor The contrast adjustment factor. Defaults to 0.0 if not
 *    provided.
 * @constructor Creates a new instance of the `ContrastSettings` class with
 *    the specified contrast adjustment factor.
 * @property factor The contrast adjustment factor. It is a double value
 *    that ranges from 0.0 to 1.0, representing the extent of the contrast
 *    adjustment to be applied.
 * @see [link](https://sksamuel.github.io/scrimage/contrast/)
 */
open class ContrastSettings(
    open val factor: Double = 0.0
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = ContrastTransformer()
    }
}