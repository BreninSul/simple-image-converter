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
import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ResizeToTransformer
import java.awt.Color

/**
 * The `ResizeToSettings` class represents the settings for resizing an
 * image to a specified resolution.
 *
 * @constructor Creates an instance of the `ResizeToSettings` class.
 * @property resolution The resolution to resize the image to. It is an
 *    instance of the `Resolution` class.
 * @property position The position of the resized image. It is an instance
 *    of the `Position` class.
 * @property colour The background color for the resized image. It is an
 *    instance of the `Color` class. The default value is transparent.
 * @see [link](https://sksamuel.github.io/scrimage/resize/)
 */
open class ResizeToSettings(
    open val resolution: Resolution,
    open val position: Position,
    open val colour: Color = Colors.Transparent.toAWT(),
) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = ResizeToTransformer()
    }
}