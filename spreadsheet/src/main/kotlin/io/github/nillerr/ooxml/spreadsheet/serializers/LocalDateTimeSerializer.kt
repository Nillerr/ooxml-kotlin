package io.github.nillerr.ooxml.spreadsheet.serializers

import io.github.nillerr.ooxml.spreadsheet.CellSerializer
import io.github.nillerr.ooxml.spreadsheet.CellSerializerRegistry
import io.github.nillerr.ooxml.spreadsheet.CellWriter
import java.time.LocalDateTime

class LocalDateTimeSerializer : CellSerializer<LocalDateTime> {
    override fun serialize(value: LocalDateTime, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeLocalDateTime(value)
    }
}
