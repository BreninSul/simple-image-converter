package com.sksamuel.scrimage.composite;

import com.sksamuel.scrimage.AwtImage
import thirdparty.romainguy.BlendingMode

open class GenericBlenderCompositeDelegate(
    open val alpha: Double = 0.0,
    open val mode: BlendingMode = BlendingMode.ADD,
): Composite{
    protected open val delegate:Composite = BlenderComposite(mode, alpha)
    override fun apply(src: AwtImage?, overlay: AwtImage?) {
        delegate.apply(src, overlay)
    }
}