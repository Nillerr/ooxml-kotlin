package io.nillerr.poi.ooxml.serializers

import io.nillerr.poi.ooxml.CellSerializer
import io.nillerr.poi.ooxml.CellSerializerRegistry
import io.nillerr.poi.ooxml.CellWriter
import java.time.LocalTime

class LocalTimeSerializer : CellSerializer<LocalTime> {
    override fun serialize(value: LocalTime, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeLocalTime(value)
    }
}
