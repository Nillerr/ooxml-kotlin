package io.nillerr.ooxml

interface CellSerializer<in T> {
    fun serialize(value: T, writer: CellWriter, serializers: CellSerializerRegistry)
}
