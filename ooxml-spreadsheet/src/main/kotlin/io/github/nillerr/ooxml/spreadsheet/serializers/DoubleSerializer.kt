package io.github.nillerr.ooxml.spreadsheet.serializers

import io.github.nillerr.ooxml.spreadsheet.CellSerializer
import io.github.nillerr.ooxml.spreadsheet.CellSerializerRegistry
import io.github.nillerr.ooxml.spreadsheet.CellWriter

class DoubleSerializer : CellSerializer<Double> {
    override fun serialize(value: Double, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeNumber(value)
    }
}
