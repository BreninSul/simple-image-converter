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
open class ImageFormat(val name: String, val supportsAnimation: Boolean = false) {
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
        val WEBP = ImageFormat("WEBP", true)
        val GIF = ImageFormat("GIF", true)
        val PNG = ImageFormat("PNG")
        val JPEG = ImageFormat("JPEG")
        val PDF = ImageFormat("PDF")
        val BMP = ImageFormat("BMP")
        val WBMP = ImageFormat("WBMP")
        val TIFF = ImageFormat("TIFF")
        val TGA = ImageFormat("TGA")
        val PNM = ImageFormat("PNM")
        val PICT = ImageFormat("PICT")
        val PSD = ImageFormat("PSD")
        val ICNS = ImageFormat("ICNS")
        val ICO = ImageFormat("ICO")
        val IFF = ImageFormat("IFF")
    }
}
