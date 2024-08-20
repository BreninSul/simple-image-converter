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
    open class GifSequenceReaderWithDelay():GifSequenceReader(){
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
}
