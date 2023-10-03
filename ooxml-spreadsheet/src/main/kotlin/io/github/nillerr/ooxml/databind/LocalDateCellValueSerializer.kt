package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.LocalDateColumnValue
import java.time.LocalDate

class LocalDateCellValueSerializer : CellValueSerializer {
    override fun serialize(value: Any?): ColumnValue? {
        return when (value) {
            is LocalDate -> LocalDateColumnValue(value)
            else -> null
        }
    }
}
