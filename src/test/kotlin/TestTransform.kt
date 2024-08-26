import com.sksamuel.scrimage.FontUtils
import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.angles.Degrees
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.OverlaySettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.Resolution
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleToSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.TransformFunctionSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter.BlurFilterSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter.SnowFilterSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.filter.WatermarkStampFilterSettings
import io.github.breninsul.simpleimageconvertor.dto.writer.ConvertSettings
import io.github.breninsul.simpleimageconvertor.service.consumer.DefaultImageConsumer
import io.github.breninsul.simpleimageconvertor.service.processor.ImageProcessorService
import org.junit.jupiter.api.Test
import java.awt.Color
import java.awt.Font
import java.io.ByteArrayOutputStream
import java.io.File

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
}
