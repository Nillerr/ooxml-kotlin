package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.BooleanColumnValue
import io.github.nillerr.ooxml.ColumnValue

class BooleanCellValueSerializer : CellValueSerializer {
    override fun serialize(value: Any?): ColumnValue? {
        return when (value) {
            is Boolean -> BooleanColumnValue(value)
            else -> null
        }
    }
}
