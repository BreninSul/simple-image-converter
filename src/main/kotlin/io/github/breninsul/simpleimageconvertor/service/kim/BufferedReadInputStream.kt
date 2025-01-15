/*
 * MIT License
 * Copyright (c) 2024 BreninSul
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.breninsul.simpleimageconvertor.service.kim

import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PushbackInputStream

/**
 * A subclass of `InputStream` that provides a buffered reading mechanism
 * for an underlying input stream. This class keeps track of all bytes
 * read from the stream into an internal buffer, allowing for operations
 * like converting to a `PushbackInputStream` while retaining the already
 * read bytes.
 *
 * This is particularly useful in scenarios where data that has already
 * been read needs to be retained or processed further (e.g., for metadata
 * extraction or re-processing the same data).
 *
 * @constructor Initializes the `BufferedReadInputStream` with the given input stream.
 * Optionally closes the underlying input stream when `close()` is called.
 *
 * @param inputStream The underlying input stream to read data from.
 * @param closeStream Set to `true` if the underlying input stream should be closed
 * when this stream is closed; otherwise, `false`.
 */
open class BufferedReadInputStream(
    protected open  val inputStream: InputStream,
    protected open val closeStream: Boolean
) : InputStream() {
    protected open val readOutputStream: ByteArrayOutputStream = ByteArrayOutputStream();

    open fun toUnreadPushbackInputStream(): PushbackInputStream {
        val alreadyRead = flushAndGetBufferBytes()
        val last=alreadyRead.last()
        val preLast=alreadyRead[alreadyRead.lastIndex-1]
        val secondPartOfStreamReadStartsAt = alreadyRead.size
        val pushbackInputStream = PushbackInputStream(inputStream, secondPartOfStreamReadStartsAt)
        pushbackInputStream.unread(alreadyRead)
        return pushbackInputStream
    }

    open fun flushAndGetBufferBytes(): ByteArray {
        readOutputStream.flush()
        val readMetadataBytes = readOutputStream.toByteArray()
        return readMetadataBytes
    }
    override fun readAllBytes(): ByteArray {
        val bytes = super.readAllBytes()
        readOutputStream.write(bytes)
        return bytes
    }

    override fun readNBytes(count: Int): ByteArray {
        val bytes = inputStream.readNBytes(count)
        readOutputStream.write(bytes)
        return bytes
    }

    override fun close(): Unit {
        if (closeStream){
            inputStream.close()
        }
    }

    override fun read(): Int {
        val nextByte = inputStream.read()
        if (nextByte != -1) {
            readOutputStream.write(nextByte)
        }
        return nextByte
    }
}
