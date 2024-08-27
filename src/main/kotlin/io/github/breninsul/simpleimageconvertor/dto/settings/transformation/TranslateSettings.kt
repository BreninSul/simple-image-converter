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
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.TranslateTransformer
import java.awt.Color

/**
 * The `TranslateSettings` class is an open class that represents the
 * settings for performing translation on an image. It provides default
 * values for the translation coordinates and colour.
 *
 * @constructor Creates a `TranslateSettings` instance.
 * @property x The translation distance in the x-axis. Default value is 0.
 * @property y The translation distance in the y-axis. Default value is 0.
 * @property colour The colour used to fill the empty space created by the
 *    translation. Default value is transparent.
 * @see [link](https://sksamuel.github.io/scrimage/translate/)
 */
open class TranslateSettings(
    open val x: Int = 0,
    open val y: Int = 0,
    open val colour: Color = Colors.Transparent.toAWT(),

    ) : TransformSettings {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = TranslateTransformer()
    }
}