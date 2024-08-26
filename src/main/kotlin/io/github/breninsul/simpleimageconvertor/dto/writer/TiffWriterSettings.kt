package io.github.breninsul.simpleimageconvertor.dto.writer

/**
 * The `TiffWriterSettings` class represents the settings for writing TIFF images.
 * It extends the `WriterSettings` interface.
 *
 * @param compressionType the compression type to be used when writing the TIFF image.
 * Possible values are "CCITT RLE", "CCITT T.4", "CCITT T.6", "LZW", "JPEG", "ZLib",
 * "PackBits", "Deflate", and "EXIF JPEG". The default value is "LZW".
 * Refer to the TIFF specification and other references for more information on each compression type.
 */
open class TiffWriterSettings(
    /**
     * https://download.java.net/media/jai-imageio/javadoc/1.1/com/sun/media/imageio/plugins/tiff/TIFFImageWriteParam.html
     * Compression Type 	Description 	Reference
     * CCITT RLE 	Modified Huffman compression 	TIFF 6.0 Specification, Section 10
     * CCITT T.4 	CCITT T.4 bilevel encoding/Group 3 facsimile compression 	TIFF 6.0 Specification, Section 11
     * CCITT T.6 	CCITT T.6 bilevel encoding/Group 4 facsimile compression 	TIFF 6.0 Specification, Section 11
     * LZW 	LZW compression 	TIFF 6.0 Specification, Section 13
     * JPEG 	"New" JPEG-in-TIFF compression 	TIFF Technical Note #2
     * ZLib 	"Deflate/Inflate" compression (see note following this table) 	Adobe Photoshop® TIFF Technical Notes (PDF)
     * PackBits 	Byte-oriented, run length compression 	TIFF 6.0 Specification, Section 9
     * Deflate 	"Zip-in-TIFF" compression (see note following this table) 	ZLIB Compressed Data Format Specification, DEFLATE Compressed Data Format Specification
     * EXIF JPEG 	EXIF-specific JPEG compression (see note following this table) 	EXIF 2.2 Specification (PDF), section 4.5.5, "Basic Structure of Thumbnail Data"
     *
     */
    val compressionType: String="LZW",
) : WriterSettings