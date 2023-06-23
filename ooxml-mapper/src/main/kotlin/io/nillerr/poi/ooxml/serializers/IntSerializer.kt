package io.nillerr.poi.ooxml.serializers

import io.nillerr.poi.ooxml.CellSerializer
import io.nillerr.poi.ooxml.CellSerializerRegistry
import io.nillerr.poi.ooxml.CellWriter

class IntSerializer : CellSerializer<Int> {
    override fun serialize(value: Int, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeNumber(value)
    }
}
