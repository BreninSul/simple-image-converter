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

import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.TakeTransformer

/**
 * The `TakeSettings` class is a subclass of `TransformSettings` interface
 * that represents the settings for the "Take" transformer.
 *
 * @property value The value that represents the amount or size of the
 *    transformation. It is an integer value and is set to 0 by default.
 * @property type The type of transformation to be applied. It is an enum
 *    value of `Type` and is set to `Type.LEFT` by default.
 * @see [link](https://sksamuel.github.io/scrimage/take/)
 */
open class TakeSettings(
    open val value: Int = 0,
    open val type: Type = Type.LEFT,
) : TransformSettings {
    enum class Type {
        LEFT, RIGHT, TOP, BOTTOM
    }

    override fun createTransformer() = transformer

    companion object {
        protected val transformer = TakeTransformer()
    }
}