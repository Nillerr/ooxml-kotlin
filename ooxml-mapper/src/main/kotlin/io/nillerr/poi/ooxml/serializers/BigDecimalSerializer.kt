package io.nillerr.poi.ooxml.serializers

import io.nillerr.poi.ooxml.CellSerializer
import io.nillerr.poi.ooxml.CellSerializerRegistry
import io.nillerr.poi.ooxml.CellWriter
import java.math.BigDecimal

class BigDecimalSerializer : CellSerializer<BigDecimal> {
    override fun serialize(value: BigDecimal, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeNumber(value)
    }
}
