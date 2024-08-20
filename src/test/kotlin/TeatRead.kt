import io.github.breninsul.simpleimageconvertor.converter.reader.GifReader
import io.github.breninsul.simpleimageconvertor.converter.reader.ImageIOReader
import io.github.breninsul.simpleimageconvertor.converter.reader.WebpReader
import io.github.breninsul.simpleimageconvertor.converter.writer.WebpWriter
import org.junit.jupiter.api.Test
import java.io.File

class TeatRead {
    val writer = WebpWriter()

    @Test
    fun readAnimatedWebp() {
        val time = System.currentTimeMillis()
        val reader = WebpReader()
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
        val reader = WebpReader()
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
        val reader = WebpReader()
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
        val reader = GifReader()
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
        val reader = ImageIOReader()
        val image = reader.read({ file.inputStream() }, listOf())
        val outFile = File("testout/IMG20240804175503_converted.webp")
        outFile.createNewFile()
        writer.write(image, listOf()) { outFile.outputStream() }

        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }
}
