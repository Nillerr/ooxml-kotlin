package io.nillerr.ooxml.serializers

import io.nillerr.ooxml.CellSerializer
import io.nillerr.ooxml.CellSerializerRegistry
import io.nillerr.ooxml.CellWriter
import io.nillerr.ooxml.Hyperlink

class HyperlinkSerializer : CellSerializer<Hyperlink> {
    override fun serialize(value: Hyperlink, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeHyperlink(value)
    }
}
