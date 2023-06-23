package io.nillerr.poi.ooxml.serializers

import io.nillerr.poi.ooxml.CellSerializer
import io.nillerr.poi.ooxml.CellSerializerRegistry
import io.nillerr.poi.ooxml.CellWriter
import io.nillerr.poi.ooxml.Hyperlink

class HyperlinkSerializer : CellSerializer<Hyperlink> {
    override fun serialize(value: Hyperlink, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeHyperlink(value)
    }
}
