package io.github.nillerr.ooxml.spreadsheet.serializers

import io.github.nillerr.ooxml.spreadsheet.CellSerializer
import io.github.nillerr.ooxml.spreadsheet.CellSerializerRegistry
import io.github.nillerr.ooxml.spreadsheet.CellWriter
import java.time.LocalTime

class LocalTimeSerializer : CellSerializer<LocalTime> {
    override fun serialize(value: LocalTime, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeLocalTime(value)
    }
}
