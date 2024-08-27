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

package io.github.breninsul.simpleimageconvertor.dto.writer

import javax.imageio.ImageWriteParam

/**
 * The `ImageIOWriterSettings` class represents the settings for writing
 * images using the ImageIO library. It extends the `WriterSettings`
 * interface and inherits from the `ImageWriteParam` class.
 *
 * This class allows you to specify various parameters for writing images,
 * such as quality, compression type, and format-specific options. You can
 * use an instance of this class to configure how an image is written using
 * the `ImageIO` class. The settings specified in an instance of this class
 * will be applied when writing an image using the `ImageIO.write` method.
 *
 * Since this class extends the `WriterSettings` interface, it also
 * inherits all the settings from that interface. You can use these
 * settings to configure general writer settings, such as output file path,
 * overwrite mode, etc.
 *
 * This class does not introduce any additional member properties or
 * methods, and is primarily used to group together the settings from its
 * parent interfaces. When using this class, you can access the settings
 * from its parent interfaces using their respective getter methods.
 */
open class ImageIOWriterSettings : WriterSettings, ImageWriteParam() {

}