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

import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.PadTransformer
import java.awt.Color

/**
 * The `PadSettings` class represents the settings for adding padding to an
 * image.
 *
 * @param left The amount of padding to add to the left side of the image.
 *    Default is 0.
 * @param top The amount of padding to add to the top of the image. Default
 *    is 0.
 * @param right The amount of padding to add to the right side of the
 *    image. Default is 0.
 * @param bottom The amount of padding to add to the bottom of the image.
 *    Default is 0.
 * @param colour The color of the padding. Default is transparent.
 * @constructor Creates a `PadSettings` object with the specified
 *    parameters.
 * @see [link](https://sksamuel.github.io/scrimage/pad/)
 */
open class PadSettings(
    open val left: Int = 0,
    open val top: Int = 0,
    open val right: Int = 0,
    open val bottom: Int = 0,
    open val colour: Color = Colors.Transparent.awt(),
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = PadTransformer()
    }
}