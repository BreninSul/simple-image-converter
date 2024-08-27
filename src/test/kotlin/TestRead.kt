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

import io.github.breninsul.simpleimageconvertor.service.consumer.DefaultImageConsumer
import io.github.breninsul.simpleimageconvertor.service.writer.WebpWriter
import org.junit.jupiter.api.Test
import java.io.File

class TestRead {
    val writer = WebpWriter()
    val reader = DefaultImageConsumer()
    @Test
    fun printSupportedFormats() {
        val consumer = DefaultImageConsumer()
        val types=consumer.supportedTypes()
        types.forEach {
            println(" - $it")
        }
    }
    @Test
    fun readAnimatedWebp() {
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/animated-webp-supported.webp").toURI())
        val image = reader.read({ file.inputStream() }, listOf())
        val outFile = File("testout/animated-webp-supported_converted.webp")
        outFile.createNewFile()
        writer.write(image, listOf()) { outFile.outputStream() }

        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }
    @Test
    fun readTransparentWebp() {
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/1_webp_a.webp").toURI())
        val image = reader.read({ file.inputStream() }, listOf())
        val outFile = File("testout/1_webp_a_converted.webp")
        outFile.createNewFile()
        writer.write(image, listOf()) { outFile.outputStream() }

        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }
    @Test
    fun readTransparentWebpLossless() {
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/1_webp_ll.webp").toURI())
        val image = reader.read({ file.inputStream() }, listOf())
        val outFile = File("testout/1_webp_ll_converted.webp")
        outFile.createNewFile()
        writer.write(image, listOf()) { outFile.outputStream() }

        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }

    @Test
    fun readGif() {
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/200w.gif").toURI())
        val image = reader.read({ file.inputStream() }, listOf())
        val outFile = File("testout/200w_converted.webp")
        outFile.createNewFile()
        writer.write(image, listOf()) { outFile.outputStream() }

        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }

    @Test
    fun readJpeg() {
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/IMG20240804175503.jpg").toURI())
        val image = reader.read({ file.inputStream() }, listOf())
        val outFile = File("testout/IMG20240804175503_converted.webp")
        outFile.createNewFile()
        writer.write(image, listOf()) { outFile.outputStream() }

        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }
    @Test
    fun readSvg() {
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/bee.svg").toURI())
        val bytes=file.readBytes()
        val image = reader.read({ bytes.inputStream() }, listOf())
        val outFile = File("testout/bee_converted.webp")
        outFile.createNewFile()
        writer.write(image, listOf()) { outFile.outputStream() }

        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }

    @Test
    fun readPDF() {
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/52.pdf").toURI())
        val image = reader.read({ file.inputStream() }, listOf())
        val outFile = File("testout/52.webp")
        outFile.createNewFile()
        writer.write(image, listOf()) { outFile.outputStream() }

        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }
}
