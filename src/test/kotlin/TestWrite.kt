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

import io.github.breninsul.simpleimageconvertor.dto.writer.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.dto.writer.ConvertSettings
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.service.consumer.DefaultImageConsumer
import io.github.breninsul.simpleimageconvertor.service.convert.DefaultImageConverter
import org.junit.jupiter.api.Test
import java.io.File

class TestWrite {
    val writer = DefaultImageConverter()
    val reader = DefaultImageConsumer()
    @Test
    fun printSupportedFormats() {
        val types=writer.supportedTypes()
        types.forEach {
            println(" - ${it.name.lowercase()}")
        }
    }
    @Test
    fun writeAnimatedWebp() {
        animated(ImageFormat.WEBP)
    }

    @Test
    fun writeAnimatedToStaticWebp() {
        animatedToStatic(ImageFormat.WEBP)
    }
    @Test
    fun writeAnimatedGif() {
        animated(ImageFormat.GIF)
    }


    @Test
    fun writeAnimatedToStaticGif() {
        animatedToStatic(ImageFormat.GIF)
    }

    @Test
    fun writeAnimatedPdf() {
        animated(ImageFormat.PDF)
    }

    @Test
    fun writeAnimatedToStaticPdf() {
        animatedToStatic(ImageFormat.PDF)
    }
    @Test
    fun writeStaticBMP() {
        writeStatic(ImageFormat.BMP)
    }
    @Test
    fun writeAnimatedToStaticIFF() {
        animatedToStatic(ImageFormat.IFF)
    }
    @Test
    fun writeAnimatedToStaticJPEG() {
        animatedToStatic(ImageFormat.JPEG)
    }

    @Test
    fun writeAnimatedToStaticPICT() {
        animatedToStatic(ImageFormat.PICT)
    }
    @Test
    fun writeStaticPNG() {
        animatedToStatic(ImageFormat.PNG)
    }
    @Test
    fun writeAnimatedToStaticPNM() {
        writeStatic(ImageFormat.PNM)
    }

    @Test
    fun writeAnimatedToStaticTGA() {
        animatedToStatic(ImageFormat.TGA)
    }
    @Test
    fun writeAnimatedToStaticTIFF() {
        animatedToStatic(ImageFormat.TIFF)
    }
    @Test
    fun writeAnimatedToStaticPSD() {
        animatedToStatic(ImageFormat.PSD)
    }
    @Test
    fun writeIconICNS() {
        icon(ImageFormat.ICNS)
    }
    @Test
    fun writeIconICO() {
        icon(ImageFormat.ICO)
    }
    fun writeStatic(format: ImageFormat) {
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/IMG20240804175503.jpg").toURI())
        val image = reader.read({ file.inputStream() }, listOf())
        val outFile = File("testwrite/static.${format.name.lowercase()}")
        outFile.createNewFile()
        writer.convert(image, listOf(ConvertSettings(format = format), AnimationToStaticSettings(strategy = AnimationToStaticSettings.StrategyEnum.MIDDLE_FRAME)), { outFile.outputStream() })
        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }
    protected open fun animatedToStatic(format: ImageFormat) {
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/animated-webp-supported.webp").toURI())
        val image = reader.read({ file.inputStream() }, listOf())
        val outFile = File("testwrite/animated-to-static.${format.name.lowercase()}")
        outFile.createNewFile()
        writer.convert(image, listOf(ConvertSettings(format = format), AnimationToStaticSettings(strategy = AnimationToStaticSettings.StrategyEnum.MIDDLE_FRAME)), { outFile.outputStream() })
        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }
    protected open fun icon(format: ImageFormat) {
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/icon.png").toURI())
        val image = reader.read({ file.inputStream() }, listOf())
        val outFile = File("testwrite/animated-to-static.${format.name.lowercase()}")
        outFile.createNewFile()
        writer.convert(image, listOf(ConvertSettings(format = format), AnimationToStaticSettings(strategy = AnimationToStaticSettings.StrategyEnum.MIDDLE_FRAME)), { outFile.outputStream() })
        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }
    protected open fun animated(format: ImageFormat) {
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/animated-webp-supported.webp").toURI())
        val image = reader.read({ file.inputStream() }, listOf())
        val outFile = File("testwrite/animated.${format.name.lowercase()}")
        outFile.createNewFile()
        writer.convert(image, listOf(ConvertSettings(format = format)), { outFile.outputStream() })
        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }

}
