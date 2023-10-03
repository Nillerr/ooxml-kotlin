package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.LocalTimeColumnValue
import java.time.LocalTime

class LocalTimeCellValueSerializer : CellValueSerializer {
    override fun serialize(value: Any?): ColumnValue? {
        return when (value) {
            is LocalTime -> LocalTimeColumnValue(value)
            else -> null
        }
    }
}
