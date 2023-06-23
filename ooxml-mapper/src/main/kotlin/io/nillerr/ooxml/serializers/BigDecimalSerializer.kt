package io.nillerr.ooxml.serializers

import io.nillerr.ooxml.CellSerializer
import io.nillerr.ooxml.CellSerializerRegistry
import io.nillerr.ooxml.CellWriter
import java.math.BigDecimal

class BigDecimalSerializer : CellSerializer<BigDecimal> {
    override fun serialize(value: BigDecimal, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeNumber(value)
    }
}
