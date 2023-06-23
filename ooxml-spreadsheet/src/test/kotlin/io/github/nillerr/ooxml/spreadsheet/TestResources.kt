package io.github.nillerr.ooxml.spreadsheet

import java.io.InputStream

fun readTestResourceAsString(name: String): String {
    return readTestResource(name).decodeToString()
}

fun readTestResource(name: String): ByteArray {
    return getTestResource(name).readAllBytes()
}

fun getTestResource(name: String): InputStream {
    val resource = WorkbookEncoderTests::class.java.getResourceAsStream(name)
    if (resource == null) {
        throw IllegalArgumentException("A resource named `$name` could not be found.")
    }

    return resource
}
