package io.github.nillerr.ooxml.spreadsheet.serializers

import io.github.nillerr.ooxml.spreadsheet.CellSerializer
import io.github.nillerr.ooxml.spreadsheet.CellSerializerRegistry
import io.github.nillerr.ooxml.spreadsheet.CellWriter
import io.github.nillerr.ooxml.spreadsheet.Hyperlink

class HyperlinkSerializer : CellSerializer<Hyperlink> {
    override fun serialize(value: Hyperlink, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeHyperlink(value)
    }
}
