package io.github.nillerr.ooxml.table

import io.github.nillerr.ooxml.spreadsheet.Hyperlink
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

sealed interface ColumnValue

object EmptyColumnValue : ColumnValue

data class StringColumnValue(val value: String) : ColumnValue

data class NumberColumnValue(val value: Number) : ColumnValue

data class BooleanColumnValue(val value: Boolean) : ColumnValue

data class InstantColumnValue(val value: Instant) : ColumnValue

data class LocalDateColumnValue(val value: LocalDate) : ColumnValue

data class LocalTimeColumnValue(val value: LocalTime) : ColumnValue

data class LocalDateTimeColumnValue(val value: LocalDateTime) : ColumnValue

data class HyperlinkColumnValue(val value: Hyperlink) : ColumnValue
