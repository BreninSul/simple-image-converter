package com.sksamuel.scrimage.composite;

import com.sksamuel.scrimage.AwtImage
import thirdparty.romainguy.BlendingMode

/**
 * This class represents a generic blender composite delegate that implements the Composite interface.
 * It allows blending images together using the BlenderComposite algorithm.
 *
 * @param alpha The alpha value used for blending the images. Default is 0.0.
 * @param mode The blending mode used for the image blending. Default is BlendingMode.ADD.
 *
 * @constructor Creates a GenericBlenderCompositeDelegate object with the specified alpha and blending mode.
 * @see [BlendingMode]
 */
open class GenericBlenderCompositeDelegate(
    open val alpha: Double = 0.0,
    open val mode: BlendingMode = BlendingMode.ADD,
): Composite{
    protected open val delegate:Composite = BlenderComposite(mode, alpha)
    override fun apply(src: AwtImage?, overlay: AwtImage?) {
        delegate.apply(src, overlay)
    }
}