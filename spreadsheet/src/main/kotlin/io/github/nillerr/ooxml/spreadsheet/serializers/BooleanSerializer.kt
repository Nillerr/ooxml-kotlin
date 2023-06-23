package io.github.nillerr.ooxml.spreadsheet.serializers

import io.github.nillerr.ooxml.spreadsheet.CellSerializer
import io.github.nillerr.ooxml.spreadsheet.CellSerializerRegistry
import io.github.nillerr.ooxml.spreadsheet.CellWriter

class BooleanSerializer : CellSerializer<Boolean> {
    override fun serialize(value: Boolean, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeBoolean(value)
    }
}
