package io.nillerr.poi.ooxml.serializers

import io.nillerr.poi.ooxml.CellSerializer
import io.nillerr.poi.ooxml.CellSerializerRegistry
import io.nillerr.poi.ooxml.CellWriter
import java.time.LocalDate

class LocalDateSerializer : CellSerializer<LocalDate> {
    override fun serialize(value: LocalDate, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeLocalDate(value)
    }
}
