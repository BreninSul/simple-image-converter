# Module simple-image-converter

This is lib to make image editing (basically conversion, but any ImageIO/scrimage action can be performed ) more universal and easy.



- Supports animation conversion between WEBP and GIF (Also can be saved as multi-paged PDF)
- Supports WEBP transparency

Based on https://github.com/haraldk/TwelveMonkeys and https://github.com/sksamuel/scrimage libs.

### To use lib just add dependency

````kotlin
dependencies {
//Other dependencies
    implementation("io.github.breninsul:simple-image-converter:2.0.6")
//Other dependencies
}
````

There is 5 main classes yor need to start working with lib
- DefaultImageConsumer : Used to read image (resolves the best reader for image by it's type)
- DefaultImageConverter : Used to write image to destination format (resolves the best write for image by it's type)
- ImageTransformer : Functional interface to edit image. There is some ready implementations like ScaleTransformer (will be added more in some time)
- Settings : Interface, implementations used to pass some special properties to reader/writers/transformers. As example ConvertSettings - used to pass image format for conversion
- DefaultImageProcessorService : Makes possible multiply dynamic transform operations. 


#### Simple conversion (Conversion animation to static with scaling and rotating)

````kotlin
package io.github.breninsul.simpleimageconvertor.example

import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.angles.Degrees
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.writer.ConvertSettings
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.Resolution
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleToSettings
import io.github.breninsul.simpleimageconvertor.service.consumer.DefaultImageConsumer
import io.github.breninsul.simpleimageconvertor.service.convert.DefaultImageConverter
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ScaleToTransformer
import java.io.File
open class SimpleExample {
    protected open val writer = DefaultImageConverter()
    protected open val reader = DefaultImageConsumer()

    fun convertWebpToGif() {
        val file = File(javaClass.classLoader.getResource("dir/animated-webp.webp").toURI())
        val image: ImageOrAnimation = file.inputStream().use { reader.read(it, listOf()) }
        val scaledImage = ScaleToTransformer().process(image, listOf(ScaleToSettings(Resolution(100, 100), ScaleMethod.FastScale)))
        val rotatedImage = ImageTransformer { img, st -> img.rotate(Degrees(90)) }.process(scaledImage)
        val outFile = File("dir/animated.gif")
        outFile.createNewFile()
        outFile.outputStream().use { writer.convert(rotatedImage, listOf(ConvertSettings(format = ImageFormat.GIF)), it) }
    }
}
````
Such way we have our ImageOrAnimation object and transform it any way (by creating ImageTransformer implementations) we want before saving.

#### Dynamic processing using 



````kotlin
package io.github.breninsul.simpleimageconvertor.example

import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.angles.Degrees
import io.github.breninsul.simpleimageconvertor.dto.settings.writer.ConvertSettings
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.Resolution
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleToSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TransformFunctionSettings
import io.github.breninsul.simpleimageconvertor.service.processor.ImageProcessorService
import java.io.File
open class DynamicExample {
    val processor = ImageProcessorService.Default

    fun convertWebpToGif() {
        val file = File(javaClass.classLoader.getResource("dir/animated-webp.webp").toURI())
        val outFile = File("dir/animated.gif")
        outFile.createNewFile()
        processor.process(file.inputStream() ,  outFile.outputStream() ,
            writerSettings =  listOf(ConvertSettings(format = ImageFormat.GIF)),
            transformSettings = listOf(ScaleToSettings(Resolution(100, 100), ScaleMethod.FastScale), TransformFunctionSettings{ img, st -> img.rotate(Degrees(90))}),
            mimeType = null
        )
    }
}
````
Such way we do the same as in simple example, but don.t operate with image,readers,writers directly.
Just passing input/output stream suppliers with list of settings and transformers.
Can be usefully for dynamic conversion (where settings and operations list will come as json form  http request)


### Supported formats:
#### Read:
- webp
- png
- pdf
- gif
- bmp
- wbp
- wbmp
- rgbe
- pnm
- pict
- bigtiff
- tif
- ico
- sgi
- pfm
- pbm
- pam
- psb
- psd
- cur
- targa
- xwd
- thumbs
- jpeg-lossless
- tga
- wmf
- pgm
- dcx
- jpg
- pct
- thumbs db
- tiff
- pcx
- svg
- pntg
- iff
- jpeg
- ppm
- hdr
- icns
- svg+xml

#### Write:
- bmp
- gif
- iff
- jpeg
- pict
- png
- pnm
- tga
- tiff
- wbmp
- webp
- psd
- icns
- ico
- pdf


#### WEBP restrictions:
Libwebp native library (JNA) is used to read WEBP https://github.com/BreninSul/WebPDecoderJN (fork of https://github.com/tduva/WebPDecoderJN)

Implementation of Libwebp for such platforms included

    Windows x86, x86-64 (1.3.2)
    Linux x86-64, arm64 (1.4.0)
    Mac x86-64, arm64 (1.4.0)

If your platform is not included please add your own implementation based on https://github.com/BreninSul/WebPDecoderJN sources 
