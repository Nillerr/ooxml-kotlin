package io.github.nillerr.ooxml.spreadsheet

import io.github.nillerr.ooxml.spreadsheet.annotation.Sheet
import io.github.nillerr.ooxml.spreadsheet.internal.asReadableProperty
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
