package io.nillerr.ooxml.serializers

import io.nillerr.ooxml.CellSerializer
import io.nillerr.ooxml.CellSerializerRegistry
import io.nillerr.ooxml.CellWriter
import java.time.Instant

class InstantSerializer : CellSerializer<Instant> {
    override fun serialize(value: Instant, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeInstant(value)
    }
}
