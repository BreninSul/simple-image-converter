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