package io.nillerr.ooxml.serializers

import io.nillerr.ooxml.CellSerializer
import io.nillerr.ooxml.CellSerializerRegistry
import io.nillerr.ooxml.CellWriter
import java.time.LocalDate

class LocalDateSerializer : CellSerializer<LocalDate> {
    override fun serialize(value: LocalDate, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeLocalDate(value)
    }
}
