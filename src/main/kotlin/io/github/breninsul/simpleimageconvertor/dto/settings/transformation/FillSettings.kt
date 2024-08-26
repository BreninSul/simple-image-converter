package io.github.breninsul.simpleimageconvertor.dto.settings.transformation

import com.sksamuel.scrimage.canvas.painters.ColorPainter
import com.sksamuel.scrimage.canvas.painters.LinearGradient
import com.sksamuel.scrimage.canvas.painters.Painter
import com.sksamuel.scrimage.canvas.painters.RadialGradient
import com.sksamuel.scrimage.canvas.painters.RandomPainter
import io.github.breninsul.simpleimageconvertor.dto.settings.TransformSettings
import io.github.breninsul.simpleimageconvertor.exception.ImageTransformerException
import java.awt.Color

open class FillSettings(
    open val colour: Color?,
    open val colourPainter: ColorPainter?,
    open val randomPainter: RandomPainter?,
    open val linearGradient: LinearGradient?,
    open val radialGradient: RadialGradient?,
) : TransformSettings {
    override fun getOrder(): Int = -10


    open fun getPainter():Painter{
        return if (colour!=null) ColorPainter(colour) else (((colourPainter?:randomPainter)?:linearGradient)?:radialGradient)?: throw ImageTransformerException("No painter/colour set")
    }
}