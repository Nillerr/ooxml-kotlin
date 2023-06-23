package io.nillerr.ooxml.internal

import kotlin.reflect.KParameter

internal object ParameterHelper {
    fun getName(parameter: KParameter): String {
        return parameter.name!!
    }
}
