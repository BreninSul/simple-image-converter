package io.github.breninsul.simpleimageconvertor.example

import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.angles.Degrees
import io.github.breninsul.simpleimageconvertor.dto.writer.ConvertSettings
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
        processor.process({ file.inputStream() }, { outFile.outputStream() },
           writerSettings =  listOf(ConvertSettings(format = ImageFormat.GIF)),
            transformSettings = listOf(ScaleToSettings(Resolution(100, 100), ScaleMethod.FastScale), TransformFunctionSettings{ img, st -> img.rotate(Degrees(90))}),
            mimeType = null
        )
    }
}