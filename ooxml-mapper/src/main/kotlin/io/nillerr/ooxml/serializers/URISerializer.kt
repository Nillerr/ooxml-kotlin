package io.nillerr.ooxml.serializers

import io.nillerr.ooxml.CellSerializer
import io.nillerr.ooxml.CellSerializerRegistry
import io.nillerr.ooxml.CellWriter
import io.nillerr.ooxml.Hyperlink
import java.net.URI
import kotlin.reflect.typeOf

class URISerializer : CellSerializer<URI> {
    override fun serialize(value: URI, writer: CellWriter, serializers: CellSerializerRegistry) {
        val hyperlinkSerializer = serializers.serializer(typeOf<Hyperlink>())

        val hyperlink = Hyperlink(text = value.toString(), uri = value)
        hyperlinkSerializer.serialize(hyperlink, writer, serializers)
    }
}
