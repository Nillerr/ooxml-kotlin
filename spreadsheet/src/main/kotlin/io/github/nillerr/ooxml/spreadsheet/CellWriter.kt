package io.github.nillerr.ooxml.spreadsheet

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface CellWriter {
    fun writeString(value: String)
    fun writeNumber(value: Number)
    fun writeBoolean(value: Boolean)
    fun writeInstant(value: Instant)
    fun writeLocalDate(value: LocalDate)
    fun writeLocalTime(value: LocalTime)
    fun writeLocalDateTime(value: LocalDateTime)
    fun writeHyperlink(value: Hyperlink)
}
