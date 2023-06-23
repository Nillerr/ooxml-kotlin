package io.nillerr.poi.ooxml.serializers

import io.nillerr.poi.ooxml.CellSerializer
import io.nillerr.poi.ooxml.CellSerializerRegistry
import io.nillerr.poi.ooxml.CellWriter

class StringSerializer : CellSerializer<String> {
    override fun serialize(value: String, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeString(value)
    }
}
