package io.nillerr.ooxml.serializers

import io.nillerr.ooxml.CellSerializer
import io.nillerr.ooxml.CellSerializerRegistry
import io.nillerr.ooxml.CellWriter

class IntSerializer : CellSerializer<Int> {
    override fun serialize(value: Int, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeNumber(value)
    }
}
