package io.nillerr.ooxml.serializers

import io.nillerr.ooxml.CellSerializer
import io.nillerr.ooxml.CellSerializerRegistry
import io.nillerr.ooxml.CellWriter
import java.time.LocalTime

class LocalTimeSerializer : CellSerializer<LocalTime> {
    override fun serialize(value: LocalTime, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeLocalTime(value)
    }
}
