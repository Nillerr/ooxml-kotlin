package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.ColumnValue

interface CellValueSerializer {
    fun serialize(value: Any?): ColumnValue?
}
