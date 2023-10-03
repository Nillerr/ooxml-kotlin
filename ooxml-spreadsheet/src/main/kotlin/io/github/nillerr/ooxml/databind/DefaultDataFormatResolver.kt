package io.github.nillerr.ooxml.databind

import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1
import kotlin.reflect.typeOf

open class DefaultDataFormatResolver : DataFormatResolver {
    override fun getDataFormat(dataFormat: String, parameter: KParameter, property: KProperty1<*, Any?>): String {
        if (dataFormat.isEmpty()) {
            return getDefaultDataFormat(property)
        }

        return dataFormat
    }

    open fun getDefaultDataFormat(property: KProperty1<*, Any?>): String {
        return when (property.returnType) {
            typeOf<Int>() -> ""
            typeOf<Double>() -> "#,##0.00"
            typeOf<BigDecimal>() -> "#,##0.00"
            typeOf<LocalDate>() -> "dd/mm/yyyy"
            typeOf<LocalTime>() -> "HH:MM"
            typeOf<LocalDateTime>() -> "dd/mm/yyy HH:MM"
            typeOf<Instant>() -> "dd/mm/yyy HH:MM:SS"
            else -> ""
        }
    }
}
