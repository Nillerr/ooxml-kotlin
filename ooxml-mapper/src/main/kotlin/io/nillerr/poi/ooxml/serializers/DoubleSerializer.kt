package io.nillerr.poi.ooxml.serializers

import io.nillerr.poi.ooxml.CellSerializer
import io.nillerr.poi.ooxml.CellSerializerRegistry
import io.nillerr.poi.ooxml.CellWriter

class DoubleSerializer : CellSerializer<Double> {
    override fun serialize(value: Double, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeNumber(value)
    }
}
