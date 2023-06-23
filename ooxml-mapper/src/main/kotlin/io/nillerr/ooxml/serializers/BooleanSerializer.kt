package io.nillerr.ooxml.serializers

import io.nillerr.ooxml.CellSerializer
import io.nillerr.ooxml.CellSerializerRegistry
import io.nillerr.ooxml.CellWriter

class BooleanSerializer : CellSerializer<Boolean> {
    override fun serialize(value: Boolean, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeBoolean(value)
    }
}
