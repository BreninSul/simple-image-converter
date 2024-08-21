package com.sksamuel.scrimage.nio.internal

import com.sksamuel.scrimage.AwtImage
import com.sksamuel.scrimage.DisposeMethod
import com.sksamuel.scrimage.nio.AnimatedGif
import com.sksamuel.scrimage.nio.StreamingGifWriter
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_ARGB
import java.io.ByteArrayOutputStream
import java.time.Duration

open class AnimatedGifWithDelay(protected open val readerDelegate:GifSequenceReaderWithDelay=GifSequenceReaderWithDelay()) :AnimatedGif( readerDelegate) {
    open fun withReader(reader:GifSequenceReaderWithDelay):AnimatedGifWithDelay{
        return AnimatedGifWithDelay(reader)
    }
    open class GifSequenceReaderWithDelay():GifSequenceReader(){
        open fun withNewFrames(newFrames:List<ImageWithDelay>):GifSequenceReaderWithDelay{
            val newReader=GifSequenceReaderWithDelay()
//            frames.clear()
//            newFrames.forEach {
//                frames.add(GifFrame(it.awt(),it.delay.toMillis().toInt(),it.disposeMethod.ordinal))
//            }
            newReader.`in`=this.`in`
            newReader.status=this.status
            newReader.width=this.width
            newReader.height=this.height
            newReader.gctFlag=this.gctFlag
            newReader.gctSize=this.gctSize

            newReader.loopCount=this.loopCount


            newReader.gct=this.gct
            newReader.lct=this.lct
            newReader.act=this.act
            newReader.bgIndex=this.bgIndex
            newReader.bgColor=this.bgColor
            newReader.lastBgColor=this.lastBgColor
            newReader.pixelAspect=this.pixelAspect

            newReader.lctFlag=this.lctFlag
            newReader.interlace=this.interlace
            newReader.ix=this.ix
            newReader.iy=this.iy
            newReader.iw=this.iw
            newReader.ih=this.ih

            newReader.lastRect=this.lastRect
            newReader.image=this.image
            newReader.lastImage=this.lastImage

            newReader.block=this.block
            newReader.blockSize=this.blockSize
            newReader.frameIndexWithLastDoNotDispose=this.frameIndexWithLastDoNotDispose
            newReader.dispose=this.dispose
            newReader.transparency=this.transparency
            newReader.delay=this.delay
            newReader.transIndex=this.transIndex

            newReader.prefix=this.prefix
            newReader.suffix=this.suffix

            newReader.pixelStack=this.pixelStack
            newReader.pixels=this.pixels

            newReader.frameCount=newFrames.size
            newReader.frames=newFrames.map { GifFrame(it.awt(),it.delay.toMillis().toInt(),it.disposeMethod.ordinal) }.toMutableList()
            return newReader
        }

        override fun bytes(): ByteArray {
            val defaultImageType = TYPE_INT_ARGB
            val arrayOutputStream = ByteArrayOutputStream()
            arrayOutputStream.use { outputStream ->
                val type=frames.firstOrNull()?.image?.type?: defaultImageType.toInt()
                val streamingGifWriter = StreamingGifWriter()
                streamingGifWriter.prepareStream(outputStream, type).use {  prepared->
                frames.forEach { frame ->
                    val imageWithDelay = getImageWithDelay(frame)
                    prepared.writeFrame(imageWithDelay.toImmutableImage(), imageWithDelay.delay,imageWithDelay.disposeMethod)
                }}
            }
            val byteArray = arrayOutputStream.toByteArray()
            return byteArray
        }
        open fun getFrameUnwrapped(n: Int): ImageWithDelay {
            val gifFrame = frames[n]
            return getImageWithDelay(gifFrame)
        }

        private fun getImageWithDelay(gifFrame: GifFrame) = ImageWithDelay(gifFrame.image, Duration.ofMillis(gifFrame.delay.toLong() ), DisposeMethod.getDisposeMethodFromId(gifFrame.disposeMethod))
    }
    open class ImageWithDelay(delegate: BufferedImage,open val delay: Duration,open val  disposeMethod:DisposeMethod) : AwtImage(delegate)

    open fun getFrameWithDelay(n: Int): ImageWithDelay {
         return readerDelegate.getFrameUnwrapped(n)
    }

    open fun getFramesWithDelay(): List<ImageWithDelay> {
        val frames: MutableList<ImageWithDelay> = ArrayList()
        for (k in 0 until frameCount) {
            frames.add(getFrameWithDelay(k))
        }
        return frames
    }
    open fun mapFrames(mapper: (ImageWithDelay) -> BufferedImage):AnimatedGifWithDelay {
        return mapFramesWithDelay{image:ImageWithDelay->ImageWithDelay(mapper.invoke(image),image.delay,image.disposeMethod)}
    }

    open fun mapFramesBuffered(mapper: (BufferedImage) -> BufferedImage):AnimatedGifWithDelay {
        return mapFramesWithDelay{image:ImageWithDelay->ImageWithDelay(mapper.invoke(image.awt()),image.delay,image.disposeMethod)}
    }

    open fun mapFramesWithDelay(mapper: (ImageWithDelay) -> ImageWithDelay):AnimatedGifWithDelay {
        val newFrames = getFramesWithDelay().map { mapper.invoke(it) }
        return withReader(readerDelegate.withNewFrames(newFrames))
    }
}
