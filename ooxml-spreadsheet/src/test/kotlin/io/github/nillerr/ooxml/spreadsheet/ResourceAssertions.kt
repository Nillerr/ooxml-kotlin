package io.github.nillerr.ooxml.spreadsheet

import java.io.ByteArrayOutputStream
import kotlin.test.assertEquals

fun assertEqualsContentsOfResource(name: String, actual: ByteArrayOutputStream) {
    val content = actual.toByteArray().decodeToString()
    val expected = readTestResourceAsString(name)
    assertEquals(expected, content)
}
