package io.github.nillerr.ooxml.spreadsheet.serializers

import io.github.nillerr.ooxml.spreadsheet.CellSerializer
import io.github.nillerr.ooxml.spreadsheet.CellSerializerRegistry
import io.github.nillerr.ooxml.spreadsheet.CellWriter
import java.time.Instant

class InstantSerializer : CellSerializer<Instant> {
    override fun serialize(value: Instant, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeInstant(value)
    }
}
