package io.github.breninsul.simpleimageconvertor.dto

open class ImageFormat(val name:String ,val supportsAnimation:Boolean=false) {
    override fun equals(other: Any?): Boolean {
        return name == (other as? ImageFormat)?.name
    }

    override fun hashCode(): Int {
        return "ImageFormat$name".hashCode()
    }

    override fun toString(): String {
        return name
    }
    companion object{
       val  WEBP=ImageFormat("WEBP",true)
       val  GIF=ImageFormat("GIF",true)
       val  PNG=ImageFormat("PNG")
       val  JPEG=ImageFormat("JPEG")
       val  PDF=ImageFormat("PDF")
       val  BMP=ImageFormat("BMP")
       val  WBMP=ImageFormat("WBMP")
       val  TIFF=ImageFormat("TIFF")
       val  TGA=ImageFormat("TGA")
       val  PNM=ImageFormat("PNM")
       val  PICT=ImageFormat("PICT")
       val  IFF=ImageFormat("IFF")
    }
}
