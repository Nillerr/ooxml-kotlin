package io.nillerr.poi.ooxml

import io.nillerr.poi.ooxml.serializers.*
import kotlin.reflect.KType
import kotlin.reflect.typeOf

class CellSerializerRegistry {
    private var serializers = mutableMapOf<KType, CellSerializer<*>>()

    init {
        register(StringSerializer())
        register(BooleanSerializer())
        register(IntSerializer())
        register(DoubleSerializer())
        register(BigDecimalSerializer())

        register(InstantSerializer())
        register(LocalDateSerializer())
        register(LocalTimeSerializer())
        register(LocalDateTimeSerializer())

        register(HyperlinkSerializer())
        register(URISerializer())
    }

    inline fun <reified T> register(serializer: CellSerializer<T>) {
        register(typeOf<T>(), serializer)
    }

    fun register(type: KType, serializer: CellSerializer<*>) {
        serializers[type] = serializer
    }

    @Suppress("UNCHECKED_CAST")
    fun serializer(type: KType): CellSerializer<Any> {
        val serializer = serializers[type]
        if (serializer == null) {
            throw WorkbookEncodingException("A serializer for the type `$type` could not be found.")
        }

        return serializer as CellSerializer<Any>
    }
}
