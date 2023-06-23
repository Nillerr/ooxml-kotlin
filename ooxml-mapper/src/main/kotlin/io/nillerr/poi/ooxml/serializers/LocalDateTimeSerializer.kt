package io.nillerr.poi.ooxml.serializers

import io.nillerr.poi.ooxml.CellSerializer
import io.nillerr.poi.ooxml.CellSerializerRegistry
import io.nillerr.poi.ooxml.CellWriter
import java.time.LocalDateTime

class LocalDateTimeSerializer : CellSerializer<LocalDateTime> {
    override fun serialize(value: LocalDateTime, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeLocalDateTime(value)
    }
}
