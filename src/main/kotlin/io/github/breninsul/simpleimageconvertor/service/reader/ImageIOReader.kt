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

package io.github.breninsul.simpleimageconvertor.service.reader

import com.sksamuel.scrimage.ImmutableImage
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import java.awt.image.BufferedImage
import java.io.InputStream
import java.util.function.Supplier
import javax.imageio.ImageIO

open class ImageIOReader(private val order: Int = Int.MAX_VALUE) : OrientedImageReader {
    protected open val supportedImageTypes = ImageIO.getReaderFormatNames().map { it.lowercase() }.toSet() + setOf("svg+xml")
    override fun supportedTypes() = supportedImageTypes

    override fun readInternal(fileStream: Supplier<InputStream>, settings: List<Settings>): ImageOrAnimation {
        val bufferedImage: BufferedImage = fileStream.get().use { ImageIO.read(it) }
        val originalImage = ImmutableImage.fromAwt(bufferedImage)
        return ImageOrAnimation(null, originalImage)
    }

    override fun getOrder(): Int {
        return order
    }
}
