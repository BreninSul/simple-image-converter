package io.github.breninsul.simpleimageconvertor.dto.settings

/**
 * The `Settings` interface represents the settings for various file
 * readers and converters. Implementations of this interface provide
 * specific settings for different types of files.
 */
interface Settings

inline fun <reified T : Settings> List<Settings>.getSetting(): T? {
    return this.getSettings<T>().firstOrNull()
}

inline fun <reified T : Settings> List<Settings>.getSettings(): List<T> {
    return this.filterIsInstance<T>() as List<T>
}
