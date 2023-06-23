package io.nillerr.ooxml.serializers

import io.nillerr.ooxml.CellSerializer
import io.nillerr.ooxml.CellSerializerRegistry
import io.nillerr.ooxml.CellWriter
import java.time.LocalDateTime

class LocalDateTimeSerializer : CellSerializer<LocalDateTime> {
    override fun serialize(value: LocalDateTime, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeLocalDateTime(value)
    }
}
