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