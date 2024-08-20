//package io.github.breninsul.simpleimageconvertor.converter
//
//import com.sksamuel.scrimage.ImmutableImage
//import com.sksamuel.scrimage.nio.PngWriter
//import com.sksamuel.scrimage.webp.WebpWriter
//import io.github.breninsul.simpleimageconvertor.converter.reader.ImageReader
//import io.github.breninsul.simpleimageconvertor.dto.ImageFormatEnum
//import io.github.breninsul.simpleimageconvertor.dto.Resolution
//import io.github.breninsul.simpleimageconvertor.dto.Settings
//import org.apache.tika.Tika
//import java.io.InputStream
//import java.io.OutputStream
//import java.util.function.Supplier
//import java.util.logging.Level
//import java.util.logging.Logger
//import kotlin.math.roundToInt
//
//open class  GeneralConverter(
//    protected open val tika:Tika=Tika() ,
//     readers:List<ImageReader>
//) {
//    protected open val imageReaders:List<ImageReader> = readers.sortedBy { it.order }
//
//
//    fun convert(
//        inputStreamSupplier: Supplier<InputStream>,
//        settings: List<Settings>,
//        keepAspectRatio: Boolean,
//        format: ImageFormatEnum,
//        outputStream: OutputStream,
//    ): OutputStream {
//        try {
//            val mimeType: String = inputStreamSupplier.get().use { tika.detect(it) }
//
//            val reader = getSuitableReader(mimeType)
//            val image=reader.read(inputStreamSupplier)
//
//            val writer =
//                when (format) {
//                    ImageFormatEnum.PNG -> PngWriter()
//                    ImageFormatEnum.WEBP -> WebpWriter()
//                    else -> throw IllegalArgumentException("Unsupported format: $format")
//                }
//            val originalImage = reader.read(inputStreamSupplier)
//
//            val resolution = originalImage.resolveResolutionWithOriginalAspectRate(keepAspectRatio, width, height)
//            val resizedImage = resize(originalImage, resolution)
//            writer.write(resizedImage, resizedImage.metadata, outputStream)
//            return outputStream
//        } catch (e: Exception) {
//            logger.log(Level.WARNING,"Error resizing image", e)
//            throw e
//        }
//    }
//
//    protected open fun getSuitableReader(mimeType: String) = imageReaders.firstOrNull { it.supports(mimeType) }?:throw IllegalStateException("No Suitable Reader for $mimeType")
//
//    protected open fun ImmutableImage.resolveResolutionWithOriginalAspectRate(
//        keepAspectRatio: Boolean,
//        width: Int,
//        height: Int,
//    ): Resolution {
//        if (!keepAspectRatio) return Resolution(width, height)
//        val destinationAspectRatio = width.toDouble() / height.toDouble()
//        val originalAspectRatio = this.countAspectRatio()
//        return if (destinationAspectRatio == originalAspectRatio) {
//            Resolution(width, height)
//        } else if (destinationAspectRatio < originalAspectRatio) {
//            val height = width / originalAspectRatio
//            Resolution(width, height.roundToInt())
//        } else {
//            val width = height * originalAspectRatio
//            Resolution(width.roundToInt(), height)
//        }
//    }
//
//    protected open fun ImmutableImage.countAspectRatio(): Double = this.width.toDouble() / this.height.toDouble()
//
//    protected open fun resize(
//        img: ImmutableImage,
//        resolution: Resolution,
//    ): ImmutableImage = img.scaleTo(resolution.width, resolution.height)
//
//    companion object {
//        val logger = Logger.getLogger(this::class.java.name)
//    }
//}
