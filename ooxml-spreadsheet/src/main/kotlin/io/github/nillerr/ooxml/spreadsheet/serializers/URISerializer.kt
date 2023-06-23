package io.github.nillerr.ooxml.spreadsheet.serializers

import io.github.nillerr.ooxml.spreadsheet.CellSerializer
import io.github.nillerr.ooxml.spreadsheet.CellSerializerRegistry
import io.github.nillerr.ooxml.spreadsheet.CellWriter
import io.github.nillerr.ooxml.spreadsheet.Hyperlink
import java.net.URI
import kotlin.reflect.typeOf

class URISerializer : CellSerializer<URI> {
    override fun serialize(value: URI, writer: CellWriter, serializers: CellSerializerRegistry) {
        val hyperlinkSerializer = serializers.serializer(typeOf<Hyperlink>())

        val hyperlink = Hyperlink(text = value.toString(), uri = value)
        hyperlinkSerializer.serialize(hyperlink, writer, serializers)
    }
}
