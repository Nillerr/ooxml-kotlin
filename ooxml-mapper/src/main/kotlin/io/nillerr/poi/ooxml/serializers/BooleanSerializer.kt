package io.nillerr.poi.ooxml.serializers

import io.nillerr.poi.ooxml.CellSerializer
import io.nillerr.poi.ooxml.CellSerializerRegistry
import io.nillerr.poi.ooxml.CellWriter

class BooleanSerializer : CellSerializer<Boolean> {
    override fun serialize(value: Boolean, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeBoolean(value)
    }
}
