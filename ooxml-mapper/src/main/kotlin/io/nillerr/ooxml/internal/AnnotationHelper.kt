package io.nillerr.ooxml.internal

import io.nillerr.ooxml.annotation.Column
import io.nillerr.ooxml.annotation.Sheet
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation

internal object AnnotationHelper {
    fun getSheetAnnotation(type: KClass<*>): Sheet {
        return type.findAnnotation<Sheet>() ?: Sheet()
    }

    fun getColumnAnnotation(parameter: KParameter): Column {
        return parameter.findAnnotation<Column>() ?: Column()
    }
}
