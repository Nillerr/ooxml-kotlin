package io.nillerr.poi.ooxml.serializers

import io.nillerr.poi.ooxml.CellSerializer
import io.nillerr.poi.ooxml.CellSerializerRegistry
import io.nillerr.poi.ooxml.CellWriter
import io.nillerr.poi.ooxml.Hyperlink
import java.net.URI
import kotlin.reflect.typeOf

class URISerializer : CellSerializer<URI> {
    override fun serialize(value: URI, writer: CellWriter, serializers: CellSerializerRegistry) {
        val hyperlinkSerializer = serializers.serializer(typeOf<Hyperlink>())

        val hyperlink = Hyperlink(text = value.toString(), uri = value)
        hyperlinkSerializer.serialize(hyperlink, writer, serializers)
    }
}
