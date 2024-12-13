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

import com.ashampoo.kim.input.ByteReader
import java.io.ByteArrayOutputStream
import java.io.InputStream

/**
 * A utility class for reading bytes from an InputStream. This implementation of the ByteReader interface
 * provides methods for sequential reading of bytes or a specified number of bytes.
 *
 * The class maintains an internal buffer (`readOutputStream`) to store all the bytes that have been read
 * from the InputStream, enabling tracking of the reading process.
 *
 * @property inputStream The InputStream to read data from.
 */
public open class BufferedInputStreamByteReader(
    protected open  val inputStream: InputStream,
    protected open val closeStream: Boolean
) : ByteReader {
    val readOutputStream: ByteArrayOutputStream = ByteArrayOutputStream();

    override val contentLength: Long
        get() = Long.MAX_VALUE

    override fun readByte(): Byte? {

        val nextByte = inputStream.read()

        if (nextByte == -1)
            return null
        readOutputStream.write(nextByte)

        return nextByte.toByte()
    }

    override fun readBytes(count: Int): ByteArray {
        val bytes = inputStream.readNBytes(count)
        readOutputStream.write(bytes)
        return bytes
    }

    override fun close(): Unit {
        if (closeStream){
            inputStream.close()
        }
    }
}
