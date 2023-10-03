package io.github.nillerr.ooxml.databind

import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.valueParameters

internal val KClass<*>.parameterProperties: List<Pair<KParameter, KProperty1<*, Any?>>>
    get() {
        val constructor = requireNotNull(primaryConstructor)
        val properties = memberProperties
        return constructor.valueParameters
            .map { parameter -> parameter to properties.first { property -> property.name == parameter.name } }
    }
