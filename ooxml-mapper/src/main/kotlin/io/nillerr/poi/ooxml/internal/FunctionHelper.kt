package io.nillerr.poi.ooxml.internal

import kotlin.reflect.KFunction
import kotlin.reflect.full.valueParameters

internal object FunctionHelper {
    fun getParameterNames(function: KFunction<*>): List<String> {
        return function.valueParameters.map { it.name!! }
    }
}
