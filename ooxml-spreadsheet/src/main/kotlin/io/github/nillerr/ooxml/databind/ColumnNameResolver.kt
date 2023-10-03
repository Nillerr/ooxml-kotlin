package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.databind.annotations.Column
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1

interface ColumnNameResolver {
    fun getColumnName(annotation: Column, parameter: KParameter, property: KProperty1<*, Any?>): String
}
