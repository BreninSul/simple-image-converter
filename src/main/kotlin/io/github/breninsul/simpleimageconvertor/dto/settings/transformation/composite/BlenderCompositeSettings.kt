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

package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.composite

import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.OperationWithImageSettings
import io.github.breninsul.simpleimageconvertor.dto.writer.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.composite.AlphaCompositeTransformer
import thirdparty.romainguy.BlendingMode

/**
 * The `BlenderCompositeSettings` class represents the settings for
 * performing the BlenderComposite operation on images. It extends the
 * `OperationWithImageSettings` class and provides additional properties
 * specific to the BlenderComposite operation.
 *
 * @constructor Creates an instance of the `BlenderCompositeSettings` class
 *    with the specified blending parameters.
 * @property alpha The alpha value for the blending operation. It
 *    determines the transparency of the blended images. Default value is
 *    0.0.
 * @property mode The blending mode used for the operation. It is an
 *    enumeration type, `BlendingMode`, with various blending options.
 *    Default value is `BlendingMode.ADD`.
 * @property image The image or animation to be processed. It can be an
 *    instance of `AnimatedGifWithDelay` or `ImmutableImage`.
 * @property animationToStaticSettings The settings for converting the
 *    animation to a static image. It is an instance of
 *    `AnimationToStaticSettings`, which represents the frame number and
 *    strategy for conversion.
 * @see [link](https://sksamuel.github.io/scrimage/composites/)
 */
open class BlenderCompositeSettings(
    open val alpha: Double = 0.0,
    open val mode: BlendingMode = BlendingMode.ADD,
    image: ImageOrAnimation,
    animationToStaticSettings: AnimationToStaticSettings? = null,
) : OperationWithImageSettings(image, animationToStaticSettings) {
    override fun createTransformer() = transformer

    companion object {
        protected val transformer = AlphaCompositeTransformer()
    }
}