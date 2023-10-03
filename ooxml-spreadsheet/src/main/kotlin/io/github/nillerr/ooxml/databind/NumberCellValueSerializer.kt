package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.NumberColumnValue

class NumberCellValueSerializer : CellValueSerializer {
    override fun serialize(value: Any?): ColumnValue? {
        return when (value) {
            is Number -> NumberColumnValue(value)
            else -> null
        }
    }
}
