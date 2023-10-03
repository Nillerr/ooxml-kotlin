package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.Hyperlink
import io.github.nillerr.ooxml.HyperlinkColumnValue

class HyperlinkCellValueSerializer : CellValueSerializer {
    override fun serialize(value: Any?): ColumnValue? {
        return when (value) {
            is Hyperlink -> HyperlinkColumnValue(value)
            else -> null
        }
    }
}
