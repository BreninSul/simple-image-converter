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

package io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter

import com.sksamuel.scrimage.FontUtils
import com.sksamuel.scrimage.color.Colors
import com.sksamuel.scrimage.filter.CaptionFilter
import com.sksamuel.scrimage.filter.Padding
import java.awt.Color
import java.awt.Font

open class CaptionFilterSettings(
    open val font: Font = FontUtils.createFont(Font.SANS_SERIF, 12),
    open val text: String = "",
    open val x: Int = 0,
    open val y: Int = 0,
    open val textColor: Color? = Color.BLACK,
    open val textAlpha: Double = 0.0,
    open val antiAlias: Boolean = false,
    open val fullWidth: Boolean = false,
    open val captionBackground: Color = Colors.Transparent.awt(),
    open val captionAlpha: Double = 0.0,
    open val padding: Padding = Padding(0, 0, 0, 0)
) : CommonTransformSettings {
    override fun toFilter() = CaptionFilter(this.text, this.x, this.y, this.font, this.textColor, this.textAlpha, this.antiAlias, this.fullWidth, this.captionBackground, this.captionAlpha, this.padding)
    override fun transformerName(): String = "CaptionFilter"
}

