package io.github.breninsul.simpleimageconvertor.dto.settings


interface Settings

inline fun <reified T : Settings> List<Settings>.getSetting(): T? {
    return this.getSettings<T>().firstOrNull()
}

inline fun <reified T : Settings> List<Settings>.getSettings(): List<T> {
    return this.filterIsInstance<T>() as List<T>
}
