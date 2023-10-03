package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.LocalDateTimeColumnValue
import java.time.LocalDateTime

class LocalDateTimeCellValueSerializer : CellValueSerializer {
    override fun serialize(value: Any?): ColumnValue? {
        return when (value) {
            is LocalDateTime -> LocalDateTimeColumnValue(value)
            else -> null
        }
    }
}
