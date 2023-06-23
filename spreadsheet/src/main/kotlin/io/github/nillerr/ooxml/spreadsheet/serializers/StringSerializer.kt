package io.github.nillerr.ooxml.spreadsheet.serializers

import io.github.nillerr.ooxml.spreadsheet.CellSerializer
import io.github.nillerr.ooxml.spreadsheet.CellSerializerRegistry
import io.github.nillerr.ooxml.spreadsheet.CellWriter

class StringSerializer : CellSerializer<String> {
    override fun serialize(value: String, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeString(value)
    }
}
