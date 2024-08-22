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
        val file = File(javaClass.classLoader.getResource("dir/animated-webp.webp").toURI())
        val outFile = File("dir/animated.gif")
        outFile.createNewFile()
        processor.process({ file.inputStream() }, { outFile.outputStream() },
            listOf(ConvertSettings(format = ImageFormat.GIF)),
            listOf(ScaleTransformer(), ImageTransformer{ img, st->img.rotate(Degrees(90))}),
            listOf(ScaleSettings(Resolution(100, 100), ScaleMethod.FastScale))
        )
    }
}