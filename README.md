This is lib to make image editing (basically conversion, but any ImageIO/scrimage action can be performed ) more universal and easy.



- Supports animation conversion between WEBP and GIF (Also can be saved as multi-paged PDF)
- Supports WEBP transparency

Based on https://github.com/haraldk/TwelveMonkeys and https://github.com/sksamuel/scrimage libs.

### To use lib just add dependency

````kotlin
dependencies {
//Other dependencies
    implementation("io.github.breninsul:simple-image-converter:${version}")
//Other dependencies
}
````

There is 5 main classes yor need to start working with lib
- DefaultImageConsumer : Used to read image (resolves the best reader for image by it's type)
- DefaultImageConverter : Used to write image to destination format (resolves the best write for image by it's type)
- ImageTransformer : Functional interface to edit image
- Settings : Interface, implementations used to pass some special properties to reader/writers/transformers. As example ConvertSettings - used to pass image format for conversion
- DefaultImageProcessorService : Makes possible multiply dynamic transform operations. There is some ready implementations like ScaleTransformer (will be added more in some time)


#### Simple conversion (Conversion animation to static with scaling and rotating)

````kotlin
package io.github.breninsul.simpleimageconvertor.example

import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.angles.Degrees
import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.writer.ConvertSettings
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.Resolution
import io.github.breninsul.simpleimageconvertor.dto.ScaleSettings
import io.github.breninsul.simpleimageconvertor.service.consumer.DefaultImageConsumer
import io.github.breninsul.simpleimageconvertor.service.convert.DefaultImageConverter
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.ScaleTransformer
import java.io.File
open class SimpleExample {
    protected open val writer = DefaultImageConverter()
    protected open val reader = DefaultImageConsumer()

    fun convertWebpToGif() {
        val file = File(javaClass.classLoader.getResource("dir/animated-webp.webp").toURI())
        val image: ConvertableImage = reader.read({ file.inputStream() }, listOf())
        val scaledImage=ScaleTransformer().process(image, listOf(ScaleSettings(Resolution(100, 100), ScaleMethod.FastScale)))
        val rotatedImage =ImageTransformer{ img, st->img.rotate(Degrees(90))}.process(scaledImage)
        val outFile = File("dir/animated.gif")
        outFile.createNewFile()
        writer.convert(rotatedImage, listOf(ConvertSettings(format = ImageFormat.GIF)), { outFile.outputStream() })
    }
}
````
Such way we have our ConvertableImage object and transform it any way (by creating ImageTransformer implementations) we want before saving.

#### Dynamic processing using 



````kotlin
package io.github.breninsul.simpleimageconvertor.example

import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.angles.Degrees
import io.github.breninsul.simpleimageconvertor.dto.writer.ConvertSettings
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.Resolution
import io.github.breninsul.simpleimageconvertor.dto.ScaleSettings
import io.github.breninsul.simpleimageconvertor.service.processor.ImageProcessorService
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.ScaleTransformer
import java.io.File
open class DynamicExample {
    val processor = ImageProcessorService.Default

    fun convertWebpToGif() {
        val format = ImageFormat.WEBP
        val file = File(javaClass.classLoader.getResource("dir/animated-webp.webp").toURI())
        val outFile = File("dir/animated.gif")
        outFile.createNewFile()
        processor.process({ file.inputStream() }, { outFile.outputStream() },
            listOf(ConvertSettings(format = ImageFormat.WEBP)),
            listOf(ScaleTransformer(), ImageTransformer{ img, st->img.rotate(Degrees(90))}),
            listOf(ScaleSettings(Resolution(100, 100), ScaleMethod.FastScale))
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

It provides:
- Named threads in executor service
- Limited by semaphore parallel jobs executor service
- Main thread blocking limited by queue executor service (parent thread will wait  till queue will have empty space for task)



#### WEBP restrictions:
Libwebp native library (JNA) is used to read WEBP https://github.com/BreninSul/WebPDecoderJN (fork of https://github.com/tduva/WebPDecoderJN)

Implementation of Libwebp for such platforms included

    Windows x86, x86-64 (1.3.2)
    Linux x86-64, arm64 (1.4.0)
    Mac x86-64, arm64 (1.4.0)

If your platform is not included please add your own implementation based on https://github.com/BreninSul/WebPDecoderJN sources 
