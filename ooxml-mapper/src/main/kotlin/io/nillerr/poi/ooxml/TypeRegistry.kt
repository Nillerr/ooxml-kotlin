package io.nillerr.poi.ooxml

import io.nillerr.poi.ooxml.internal.TypeHelper
import kotlin.reflect.KClass

class TypeRegistry {
    private val typeInfo = mutableMapOf<KClass<*>, TypeInfo>()

    fun getTypeInfo(type: KClass<*>): TypeInfo {
        return typeInfo.getOrPut(type) { TypeHelper.createTypeInfo(type) }
    }
}