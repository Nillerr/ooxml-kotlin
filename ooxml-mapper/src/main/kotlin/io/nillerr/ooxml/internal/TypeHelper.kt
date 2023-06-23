package io.nillerr.ooxml.internal

import io.nillerr.ooxml.TypeInfo
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.valueParameters

internal object TypeHelper {
    fun getPrimaryConstructor(type: KClass<*>): KFunction<*> {
        return type.primaryConstructor!!
    }

    fun getMemberProperties(type: KClass<*>): Map<String, KProperty1<*, *>> {
        return type.memberProperties.associateBy { it.name }
    }

    fun createTypeInfo(type: KClass<*>): TypeInfo {
        val sheet = AnnotationHelper.getSheetAnnotation(type)
        val properties = getMemberProperties(type)
        val constructor = getPrimaryConstructor(type)
        val parameterNames = FunctionHelper.getParameterNames(constructor)
        val columns = ColumnHelper.createColumnInfo(constructor.valueParameters, properties)
        return TypeInfo(type, sheet, properties, parameterNames, columns)
    }
}
