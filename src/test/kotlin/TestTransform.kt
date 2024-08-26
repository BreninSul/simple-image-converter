import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.angles.Degrees
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.OverlaySettings
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.Resolution
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleToSettings
import io.github.breninsul.simpleimageconvertor.dto.writer.ConvertSettings
import io.github.breninsul.simpleimageconvertor.service.consumer.DefaultImageConsumer
import io.github.breninsul.simpleimageconvertor.service.processor.ImageProcessorService
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.OverlayTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ScaleToTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.predefined.ScaleTransformer
import org.junit.jupiter.api.Test
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
        processor.process({ file.inputStream() }, { outFile.outputStream() },
            listOf(ConvertSettings(format = format)),
            listOf(ScaleToTransformer(), ImageTransformer { img, st -> img.rotate(Degrees(90)) }),
            listOf(ScaleToSettings(Resolution(100, 100), ScaleMethod.FastScale)),
            listOf(),
            null
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
        processor.process({ file.inputStream() }, { secondAnimationOutputStream },
            listOf(ConvertSettings(format = format)),
            listOf(ScaleToTransformer(), ImageTransformer { img, st -> img.rotate(Degrees(90)) }),
            listOf(ScaleToSettings(Resolution(100, 100), ScaleMethod.FastScale)),
            listOf(),
        )
        val secondAnimationBytes=secondAnimationOutputStream.toByteArray()
        val secondAnimation = reader.read({ secondAnimationBytes.inputStream() }, listOf())
        processor.process({ file.inputStream() }, { outFile.outputStream() },
            listOf(ConvertSettings(format = format)),
            listOf(OverlayTransformer()),
            listOf(OverlaySettings(0, 0, secondAnimation)),
        )
        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }
}
