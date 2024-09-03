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

import com.sksamuel.scrimage.FontUtils
import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.angles.Degrees
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.*
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter.SnowFilterSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter.WatermarkStampFilterSettings
import io.github.breninsul.simpleimageconvertor.dto.writer.ConvertSettings
import io.github.breninsul.simpleimageconvertor.dto.writer.WebpWriterSettings
import io.github.breninsul.simpleimageconvertor.service.consumer.DefaultImageConsumer
import io.github.breninsul.simpleimageconvertor.service.processor.ImageProcessorService
import org.junit.jupiter.api.Test
import java.awt.Color
import java.awt.Font
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO


class TestTransform {
    val processor = ImageProcessorService.Default
    val reader = DefaultImageConsumer()

    @Test
    fun scaleAnimation() {
        val format = ImageFormat.WEBP
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/animated-webp-supported.webp").toURI())
        val outFile = File("testtransform/animated-scaled.${format.name.lowercase()}")
        outFile.createNewFile()

        processor.process(
            { file.inputStream() }, { outFile.outputStream() },
            writerSettings = listOf(ConvertSettings(format = format)),
            transformSettings = listOf(
                ScaleToSettings(Resolution(1000, 1000), ScaleMethod.FastScale),
                TransformFunctionSettings { img, st -> img.rotate(Degrees(90)) },
                WatermarkStampFilterSettings("Test!!!", alpha = 0.9, colour = Color.RED, font = FontUtils.createFont(Font.SANS_SERIF, 60))
            ),
            readerSettings = listOf(),
        )
        processor.process({ file.inputStream() }, { outFile.outputStream() },
            listOf(
                ScaleToSettings(Resolution(640, 640, true)),
                WebpWriterSettings(z = 100, lossless = false),
                ConvertSettings(format = ImageFormat.WEBP)
            )
        )
        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }

    @Test
    fun overlayAnimation() {
        val format = ImageFormat.WEBP
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/animated-webp-supported.webp").toURI())
        val outFile = File("testtransform/animated-overlay.${format.name.lowercase()}")
        val secondAnimationOutputStream = ByteArrayOutputStream()
        processor.process(
            { file.inputStream() }, { secondAnimationOutputStream },
            writerSettings = listOf(ConvertSettings(format = format)),
            transformSettings = listOf(
                ScaleToSettings(Resolution(200, 200), ScaleMethod.FastScale),
                TransformFunctionSettings { img, st -> img.rotate(Degrees(90)) },
                SnowFilterSettings()
            ),
            readerSettings = listOf(),
        )
        val secondAnimationBytes = secondAnimationOutputStream.toByteArray()
        val secondAnimation = reader.read({ secondAnimationBytes.inputStream() }, listOf())
        processor.process(
            { file.inputStream() }, { outFile.outputStream() },
            writerSettings = listOf(ConvertSettings(format = format)),
            transformSettings = listOf(
                OverlaySettings(0, 0, secondAnimation),
            ),
        )
        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }





    @Test
    fun tstAnimation() {
        val format = ImageFormat.WEBP
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/tst.jpg").toURI())
        val img=ImageIO.read(file)
        val image = ImmutableImage.fromAwt(img)
        val resolution=Resolution(1920,1080,true).resolveResolutionWithOriginalAspectRate(image)
        val new=resizeImage(img, resolution.width, resolution.height)
        val outFile2 = File("testtransform/tst2.jpg")
        ImageIO.write(new, "jpg", outFile2);
        val outFile = File("testtransform/tst.${format.name.lowercase()}")
        processor.process(
            {file.inputStream()},
            { outFile.outputStream() },
            listOf(ConvertSettings(format = ImageFormat(format.name.uppercase()))),
            listOf(ScaleToSettings(Resolution(1920,1080,true),ScaleMethod.FastScale)),
        )
        val new2=ImageIO.read(outFile)
        val l= listOf(img.width,img.height,image.width,image.height,new.width,new.height,new2.width,new2.height)
//        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
        println(l)
    }

    @Throws(IOException::class)
    fun resizeImage(originalImage: BufferedImage, targetWidth: Int, targetHeight: Int): BufferedImage {
        val resultingImage: Image = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT)
        val outputImage = BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB)
        outputImage.graphics.drawImage(resultingImage, 0, 0, null)
        return outputImage
    }
}
