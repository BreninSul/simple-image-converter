import io.github.breninsul.simpleimageconvertor.service.consumer.DefaultImageConsumer
import io.github.breninsul.simpleimageconvertor.service.reader.*
import io.github.breninsul.simpleimageconvertor.service.writer.WebpWriter
import org.junit.jupiter.api.Test
import java.io.File

class TeatRead {
    val writer = WebpWriter()
    val reader = DefaultImageConsumer()
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
