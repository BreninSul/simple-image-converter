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

import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.writer.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.OverlayTransformer

/**
 * The `OverlaySettings` class represents the settings for overlaying
 * an image on top of another image. It is a subclass of the
 * `OperationWithImageSettings` class.
 *
 * @constructor Creates a new instance of `OverlaySettings` with the
 *    specified parameters.
 * @property x The x-coordinate of the top-left corner of the overlay image
 *    on the base image. Default value is 0.
 * @property y The y-coordinate of the top-left corner of the overlay image
 *    on the base image. Default value is 0.
 * @property image The image or animation to be overlayed onto the base
 *    image.
 * @property animationToStaticSettings The settings for converting an
 *    animation to a static image. It is an instance of the
 *    `AnimationToStaticSettings` class.
 * @see [link](https://sksamuel.github.io/scrimage/overlay/)
 */
open class OverlaySettings(
    open val x: Int = 0,
    open val y: Int = 0,
    image: ImageOrAnimation,
    animationToStaticSettings: AnimationToStaticSettings? = null,
) : OperationWithImageSettings(image, animationToStaticSettings) {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = OverlayTransformer()
    }
}