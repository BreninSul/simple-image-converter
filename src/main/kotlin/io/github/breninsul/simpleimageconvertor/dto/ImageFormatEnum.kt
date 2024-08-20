package io.github.breninsul.simpleimageconvertor.dto

enum class ImageFormatEnum(val supportsAnimation:Boolean=false) {
    WEBP(true),
    GIF(true),
    PNG,
    JPEG,
    PDF,
    BMP,
    WBMP,
    TIFF,
    TGA,
    SGI,
    PNM,
    PICT,
    PCX,
    IFF,
}
