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

package io.github.breninsul.simpleimageconvertor.dto

/**
 * Represent a specific image format.
 *
 * @param name The name of the image format.
 * @param supportsAnimation Indicates whether the image format supports
 *    animation. Default value is false.
 * @constructor Creates a new instance of the ImageFormat class with the
 *    given name and animation support.
 * @property name The name of the image format.
 * @property mimeTypes - Mime types for specified image format
 * @property supportsAnimation Indicates whether the image format supports
 *    animation.
 * @see ImageFormat.WEBP
 * @see ImageFormat.GIF
 * @see ImageFormat.PNG
 * @see ImageFormat.JPEG
 * @see ImageFormat.PDF
 * @see ImageFormat.BMP
 * @see ImageFormat.WBMP
 * @see ImageFormat.TIFF
 * @see ImageFormat.TGA
 * @see ImageFormat.PNM
 * @see ImageFormat.PICT
 * @see ImageFormat.PSD
 * @see ImageFormat.ICNS
 * @see ImageFormat.ICO
 * @see ImageFormat.IFF
 */
open class ImageFormat(val name: String,val mimeTypes:List<String>, val supportsAnimation: Boolean = false) {
    constructor( name: String, mimeType:String,  supportsAnimation: Boolean = false):this(name, listOf(mimeType), supportsAnimation)
    constructor( name: String,  supportsAnimation: Boolean = false):this(name, DEFAULT_FORMATS.firstOrNull { it.name.equals(name, true) }?.mimeTypes?: listOf(), supportsAnimation)

    override fun equals(other: Any?): Boolean {
        return name == (other as? ImageFormat)?.name
    }

    override fun hashCode(): Int {
        return "ImageFormat$name".hashCode()
    }

    override fun toString(): String {
        return name
    }

    companion object {
        val WEBP = ImageFormat("WEBP","image/webp", true)
        val GIF = ImageFormat("GIF", "image/gif",true)
        val PNG = ImageFormat("PNG","image/png")
        val JPEG = ImageFormat("JPEG","image/jpeg")
        val PDF = ImageFormat("PDF","application/pdf")
        val BMP = ImageFormat("BMP","image/bmp")
        val WBMP = ImageFormat("WBMP","image/vnd.wap.wbmp")
        val TIFF = ImageFormat("TIFF","image/tiff")
        val TGA = ImageFormat("TGA", listOf("image/tga","image/x-tga","application/tga","application/x-tga"))
        val PNM = ImageFormat("PNM","image/x-portable-anymap")
        val PICT = ImageFormat("PICT","image/x-pict")
        val PSD = ImageFormat("PSD","image/vnd.adobe.photoshop")
        val ICNS = ImageFormat("ICNS","image/x-icns")
        val ICO = ImageFormat("ICO","image/x-icon")
        val IFF = ImageFormat("IFF", listOf("image/iff","image/x-iff","application/iff","application/x-iff"))
        //Some formats can be added or removed dynamically on startup
        val DEFAULT_FORMATS = mutableListOf(WEBP,GIF,PNG,JPEG,PDF,BMP,WBMP,TIFF,TGA,PNM,PICT,PSD,ICNS,ICO,IFF)
    }
}
