package io.nillerr.ooxml

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface CellReader {
    fun readString(): String
    fun readInt(): Int
    fun readDouble(): Double
    fun readBoolean(): Boolean
    fun readInstant(): Instant
    fun readLocalDate(): LocalDate
    fun readLocalTime(): LocalTime
    fun readLocalDateTime(): LocalDateTime
    fun readHyperlink(): Hyperlink
}
