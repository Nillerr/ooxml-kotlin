package io.github.nillerr.ooxml.spreadsheet

interface CellSerializer<in T> {
    fun serialize(value: T, writer: CellWriter, serializers: CellSerializerRegistry)
}
