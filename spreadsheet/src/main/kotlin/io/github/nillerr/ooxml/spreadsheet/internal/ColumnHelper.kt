package io.github.nillerr.ooxml.spreadsheet.internal

import io.github.nillerr.ooxml.spreadsheet.ColumnInfo
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1

internal object ColumnHelper {
    fun createColumnInfo(parameters: List<KParameter>, properties: Map<String, KProperty1<*, *>>): Map<String, ColumnInfo> {
        return parameters.associateBy(
            keySelector = { ParameterHelper.getName(it) },
            valueTransform = { parameter ->
                val parameterName = ParameterHelper.getName(parameter)
                val property = properties.getValue(parameterName)

                ColumnInfo(
                    annotation = AnnotationHelper.getColumnAnnotation(parameter),
                    propertyName = property.name,
                    propertyType = property.returnType,
                )
            }
        )
    }
}
