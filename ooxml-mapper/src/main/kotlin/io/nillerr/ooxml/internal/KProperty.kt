package io.nillerr.ooxml.internal

import kotlin.reflect.KProperty1

@Suppress("UNCHECKED_CAST")
internal fun KProperty1<*, *>.asReadableProperty(): KProperty1<Any, Any?> {
    return this as KProperty1<Any, Any?>
}
