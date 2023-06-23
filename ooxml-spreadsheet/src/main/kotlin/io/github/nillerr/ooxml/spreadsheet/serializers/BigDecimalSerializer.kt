package io.github.nillerr.ooxml.spreadsheet.serializers

import io.github.nillerr.ooxml.spreadsheet.CellSerializer
import io.github.nillerr.ooxml.spreadsheet.CellSerializerRegistry
import io.github.nillerr.ooxml.spreadsheet.CellWriter
import java.math.BigDecimal

class BigDecimalSerializer : CellSerializer<BigDecimal> {
    override fun serialize(value: BigDecimal, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeNumber(value)
    }
}
