package io.github.breninsul.simpleimageconvertor.service.reader

import com.madgag.gif.fmsware.AnimatedGifEncoder
import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay
import com.sksamuel.scrimage.nio.internal.AnimatedGifWithDelay.GifSequenceReaderWithDelay
import io.github.breninsul.simpleimageconvertor.dto.ImageOrAnimation
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
import webpdecoderjn.WebPDecoder
import java.io.InputStream
import java.nio.file.Files
import java.util.function.Supplier
import kotlin.io.path.deleteIfExists
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream


open class WebpReader(private val order: Int = 1) : ImageReader {
    init {
        WebPDecoder.init()
    }

    protected open val supportedImageTypes = setOf("webp")

    override fun read(fileStream: Supplier<InputStream>, settings: List<Settings>): ImageOrAnimation {
        val originalBytes = fileStream.get().use { it.readAllBytes() }
        val isAnimation = originalBytes.inputStream().isWebpAnimated()
        val decodedImage: WebPDecoder.WebPImage = fileStream.get().use { WebPDecoder.decode(originalBytes) }

        if (!isAnimation) {
            return ImageOrAnimation(null, ImmutableImage.fromAwt(decodedImage.frames.first().img))
        } else {
            val encoder = AnimatedGifEncoder()
            val tempFile = Files.createTempFile("AnimationReaderWebp", ".gif")
            tempFile.outputStream().use { outputStream ->
                encoder.start(outputStream)
                encoder.setRepeat(decodedImage.loopCount)
                encoder.setSize(decodedImage.canvasWidth, decodedImage.canvasHeight)
                encoder.setBackground(decodedImage.bgColor)
                decodedImage.frames.forEach {
                    encoder.setDelay(it.delay)
                    encoder.addFrame(it.img)
                }
                encoder.finish()
            }
            val reader = tempFile.inputStream().use {
                val reader = GifSequenceReaderWithDelay()
                reader.read(it)
                reader
            }
            tempFile.deleteIfExists()
            val gif = AnimatedGifWithDelay(reader)
            return ImageOrAnimation(gif, null)
        }
    }

    protected open fun InputStream.isWebpAnimated(): Boolean {
        this.use { inputStream ->
            var result = false
            inputStream.skip(12)
            val buf = ByteArray(4)
            val i = inputStream.read(buf)
            if ("VP8X" == String(buf, 0, i)) {
                inputStream.skip(12)
                result = (inputStream.read(buf) == 4 && (buf[3].toInt() and 0x00000002) != 0)
            }
            return result
        }
    }

    override fun supportedTypes(): Set<String> {
        return supportedImageTypes
    }


    override fun getOrder(): Int {
        return order
    }

}
