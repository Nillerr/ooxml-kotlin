package io.github.nillerr.ooxml.spreadsheet.serializers

import io.github.nillerr.ooxml.spreadsheet.CellSerializer
import io.github.nillerr.ooxml.spreadsheet.CellSerializerRegistry
import io.github.nillerr.ooxml.spreadsheet.CellWriter
import java.time.LocalDate

class LocalDateSerializer : CellSerializer<LocalDate> {
    override fun serialize(value: LocalDate, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeLocalDate(value)
    }
}
