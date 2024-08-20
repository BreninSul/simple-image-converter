package io.github.breninsul.simpleimageconvertor.converter.reader

import com.sksamuel.scrimage.format.png.PngReader
import io.github.breninsul.simpleimageconvertor.dto.ConvertableImage
import io.github.breninsul.simpleimageconvertor.dto.Settings
import java.io.InputStream
import java.util.function.Supplier

open class PngReader(private val order:Int=1) : ImageReader {
    protected open val supportedImageTypes = setOf("png")
    override fun supportedTypes()=supportedImageTypes
    override fun read(fileStream: Supplier<InputStream>,settings: List<Settings>): ConvertableImage {
        val originalImage = fileStream.get().use { PngReader().read(it) }
        return ConvertableImage(null,originalImage)
    }



    override fun getOrder(): Int {
       return order
    }
}
