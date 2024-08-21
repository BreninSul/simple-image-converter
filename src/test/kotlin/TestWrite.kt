import io.github.breninsul.simpleimageconvertor.dto.AnimationToStaticSettings
import io.github.breninsul.simpleimageconvertor.dto.ConvertSettings
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.service.consumer.DefaultImageConsumer
import io.github.breninsul.simpleimageconvertor.service.convert.DefaultImageConverter
import org.junit.jupiter.api.Test
import java.io.File

class TestWrite {
    val writer = DefaultImageConverter()
    val reader = DefaultImageConsumer()
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
