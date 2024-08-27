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

package com.sksamuel.scrimage.composite;

import com.sksamuel.scrimage.AwtImage
import thirdparty.romainguy.BlendingMode

/**
 * This class represents a generic blender composite delegate that
 * implements the Composite interface. It allows blending images together
 * using the BlenderComposite algorithm.
 *
 * @param alpha The alpha value used for blending the images. Default is
 *    0.0.
 * @param mode The blending mode used for the image blending. Default is
 *    BlendingMode.ADD.
 * @constructor Creates a GenericBlenderCompositeDelegate object with the
 *    specified alpha and blending mode.
 * @see [BlendingMode]
 */
open class GenericBlenderCompositeDelegate(
    open val alpha: Double = 0.0,
    open val mode: BlendingMode = BlendingMode.ADD,
) : Composite {
    protected open val delegate: Composite = BlenderComposite(mode, alpha)
    override fun apply(src: AwtImage?, overlay: AwtImage?) {
        delegate.apply(src, overlay)
    }
}