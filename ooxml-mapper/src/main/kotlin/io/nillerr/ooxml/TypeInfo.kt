package io.nillerr.ooxml

import io.nillerr.ooxml.annotation.Sheet
import io.nillerr.ooxml.internal.asReadableProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

data class TypeInfo(
    val type: KClass<*>,
    val sheet: Sheet,
    private val properties: Map<String, KProperty1<*, *>>,
    val parameterNames: List<String>,
    private val columns: Map<String, ColumnInfo>,
) {
    fun getColumn(parameterName: String): ColumnInfo {
        return columns[parameterName]!!
    }

    fun getProperty(parameterName: String): KProperty1<Any, Any?> {
        return properties[parameterName]!!.asReadableProperty()
    }
}
