package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.databind.annotations.Column
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1

open class DefaultColumnNameResolver : ColumnNameResolver {
    override fun getColumnName(annotation: Column, parameter: KParameter, property: KProperty1<*, Any?>): String {
        return when {
            annotation.name.isNotEmpty() -> annotation.name
            else -> property.name
        }
    }
}
