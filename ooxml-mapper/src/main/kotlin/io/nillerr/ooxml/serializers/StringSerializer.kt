package io.nillerr.ooxml.serializers

import io.nillerr.ooxml.CellSerializer
import io.nillerr.ooxml.CellSerializerRegistry
import io.nillerr.ooxml.CellWriter

class StringSerializer : CellSerializer<String> {
    override fun serialize(value: String, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeString(value)
    }
}
