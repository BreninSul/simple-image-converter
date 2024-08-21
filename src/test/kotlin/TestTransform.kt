import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.angles.Degrees
import io.github.breninsul.simpleimageconvertor.dto.*
import io.github.breninsul.simpleimageconvertor.service.processor.ImageProcessorService
import io.github.breninsul.simpleimageconvertor.service.transformer.ImageTransformer
import io.github.breninsul.simpleimageconvertor.service.transformer.ScaleTransformer
import org.junit.jupiter.api.Test
import java.io.File

class TestTransform {
    val processor = ImageProcessorService.Default

    @Test
    fun scaleAnimation() {
        val format = ImageFormat.WEBP
        val time = System.currentTimeMillis()
        val file = File(javaClass.classLoader.getResource("images/animated-webp-supported.webp").toURI())
        val outFile = File("testtransform/animated.${format.name.lowercase()}")
        outFile.createNewFile()
        processor.process({ file.inputStream() }, { outFile.outputStream() },
            listOf(ConvertSettings(format = format)),
            listOf(ScaleTransformer(), ImageTransformer{ img, st->img.rotate(Degrees(90))}),
            listOf(ScaleSettings(Resolution(100, 100), ScaleMethod.FastScale))
        )
        println("${outFile.absolutePath} took ${System.currentTimeMillis() - time}ms")
    }


}
