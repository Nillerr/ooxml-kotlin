package io.github.nillerr.ooxml.databind

import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1

interface DataFormatResolver {
    fun getDataFormat(dataFormat: String, parameter: KParameter, property: KProperty1<*, Any?>): String
}
