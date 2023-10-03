package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.InstantColumnValue
import java.time.Instant

class InstantCellValueSerializer : CellValueSerializer {
    override fun serialize(value: Any?): ColumnValue? {
        return when (value) {
            is Instant -> InstantColumnValue(value)
            else -> null
        }
    }
}
