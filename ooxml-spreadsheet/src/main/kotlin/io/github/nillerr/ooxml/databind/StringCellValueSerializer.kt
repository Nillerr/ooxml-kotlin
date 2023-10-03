package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.StringColumnValue

class StringCellValueSerializer : CellValueSerializer {
    override fun serialize(value: Any?): ColumnValue? {
        return when (value) {
            is String -> StringColumnValue(value)
            else -> null
        }
    }
}
