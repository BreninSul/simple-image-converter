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
import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.FitTransformer
import java.awt.Color

/**
 * The `FitSettings` class is an open class that represents the settings
 * for fitting an image into a specified resolution.
 *
 * @property resolution The resolution to fit the image into. It is an
 *    instance of [Resolution].
 * @property colour The color to use for the background if the image does
 *    not cover the entire resolution. It is an instance of [Color].
 * @property scaleMethod The scaling method to use for the image. It is an
 *    instance of [ScaleMethod].
 * @property position The position to place the image within the resolution
 *    if the aspect ratios are different. It is an instance of [Position].
 * @see [link](https://sksamuel.github.io/scrimage/fit/)
 */
open class FitSettings(
    open val resolution: Resolution,
    open val colour: Color = Colors.Transparent.toAWT(),
    open val scaleMethod: ScaleMethod = ScaleMethod.Bicubic,
    open val position: Position = Position.Center,
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = FitTransformer()
    }
}
