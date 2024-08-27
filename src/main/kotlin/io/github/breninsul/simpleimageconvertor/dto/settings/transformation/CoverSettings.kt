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

import com.sksamuel.scrimage.Position
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.CoverTransformer

/**
 * The `CoverSettings` class represents the settings for applying a cover
 * effect to images.
 *
 * @param resolution The resolution of the transformed image. It must be an
 *    instance of [Resolution].
 * @param position The position of the cover effect. It defaults to
 *    [Position.TopLeft].
 * @constructor Creates a new instance of `CoverSettings` with the
 *    specified resolution and position.
 * @see [link](https://sksamuel.github.io/scrimage/cover/)
 */
open class CoverSettings(
    open val resolution: Resolution,
    open val position: Position = Position.TopLeft,
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = CoverTransformer()
    }
}