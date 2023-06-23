package io.github.nillerr.ooxml.spreadsheet.internal

import io.github.nillerr.ooxml.spreadsheet.annotation.Column
import io.github.nillerr.ooxml.spreadsheet.annotation.Sheet
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation

internal object AnnotationHelper {
    fun getSheetAnnotation(type: KClass<*>): Sheet {
        val annotation = type.findAnnotation<Sheet>()
        if (annotation == null) {
            return Sheet()
        }

        return annotation
    }

    fun getColumnAnnotation(parameter: KParameter): Column {
        val annotation = parameter.findAnnotation<Column>()
        if (annotation == null) {
            return Column()
        }

        return annotation
    }
}
