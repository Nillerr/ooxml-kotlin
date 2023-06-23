package io.nillerr.poi.ooxml

interface CellSerializer<in T> {
    fun serialize(value: T, writer: CellWriter, serializers: CellSerializerRegistry)
}
