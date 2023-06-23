package io.nillerr.ooxml.internal

internal inline fun <T, V> List<T>.firstOrLast(selector: (T) -> V, predicate: (V) -> Boolean): V {
    return selector(firstOrNull { predicate(selector(it)) } ?: last())
}
