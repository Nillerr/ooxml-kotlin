package io.nillerr.poi.ooxml.serializers

import io.nillerr.poi.ooxml.CellSerializer
import io.nillerr.poi.ooxml.CellSerializerRegistry
import io.nillerr.poi.ooxml.CellWriter
import java.time.Instant

class InstantSerializer : CellSerializer<Instant> {
    override fun serialize(value: Instant, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeInstant(value)
    }
}
