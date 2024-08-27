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

import com.sksamuel.scrimage.angles.Radians
import com.sksamuel.scrimage.color.Colors
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.RotateTransformer
import java.awt.Color

/**
 * The `RotateSettings` class is an open class that represents the settings
 * for rotating images. It extends the `TransformSettings` interface.
 *
 * @constructor Creates an instance of the `RotateSettings` class.
 * @property degree The degree of rotation as a double value. The default
 *    value is 0.0.
 * @property colour The color to fill the background with during rotation
 *    as an instance of the `Color` class. The default value is
 *    `Colors.Transparent.toAWT()`.
 * @see [link](https://sksamuel.github.io/scrimage/rotate/)
 */
open class RotateSettings(
    open val degree: Double = 0.0,
    open val colour: Color = Colors.Transparent.toAWT(),
) : TransformSettings {
    override fun createTransformer() = transformer

    open fun getRadians(): Radians = Radians(Math.toRadians(degree))

    companion object {
        protected val transformer = RotateTransformer()
    }
}
