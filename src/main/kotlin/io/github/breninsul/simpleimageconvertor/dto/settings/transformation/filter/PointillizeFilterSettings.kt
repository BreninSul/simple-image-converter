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

import com.sksamuel.scrimage.filter.PointillizeFilter
import com.sksamuel.scrimage.filter.PointillizeGridType
import java.awt.Color

open class PointillizeFilterSettings(
    open val angle: Double = 0.0,
    open val scale: Int = 6,
    open val edgeThickness: Double = 0.4,
    open val edgeColor: Color = Color.decode("0xff000000"),
    open val fadeEdges: Boolean = false,
    open val fuzziness: Double = 0.1,
    open val gridType: PointillizeGridType = PointillizeGridType.Random,

    ) : CommonTransformSettings {
    override fun toFilter() = PointillizeFilter(angle.toFloat(), scale, edgeThickness.toFloat(), edgeColor.rgb, fadeEdges, fuzziness.toFloat(), gridType)
    override fun transformerName(): String = "PointillizeFilter"
}
