package io.github.breninsul.simpleimageconvertor.converter.writer

import com.sksamuel.scrimage.AwtImage
import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.metadata.ImageMetadata
import com.sksamuel.scrimage.nio.ImageWriter
import io.github.breninsul.simpleimageconvertor.dto.*
import java.io.OutputStream
import java.util.function.Supplier
import javax.imageio.IIOImage
import javax.imageio.ImageIO

open class WbmpWriter : StaticImageWriter {
    open class WbmpTwelveMonkeysWriter() : ImageWriter {
        open fun format(): String?="wbmp"

        override fun write(image: AwtImage, metadata: ImageMetadata?, out: OutputStream?) {
            val writer = ImageIO.getImageWritersByFormatName(format()).next()
            val ios = ImageIO.createImageOutputStream(out)
            val params = writer.defaultWriteParam
            writer.output = ios
            writer.write(null, IIOImage(image.awt(), null, null), params)
            ios.close()
            writer.dispose()
        }
    }
    protected open val supportedImageTypes = setOf(ImageFormatEnum.WBMP)

    override fun supportedTypes(): Set<ImageFormatEnum> {
        return supportedImageTypes
    }
    override fun writeStatic(image: ImmutableImage, settings: List<Settings>, out: Supplier<OutputStream>) {
        val writer=WbmpTwelveMonkeysWriter()
        writer.write(image,image.metadata,out.get())
    }


    override fun getOrder(): Int {
        return 1
    }

}