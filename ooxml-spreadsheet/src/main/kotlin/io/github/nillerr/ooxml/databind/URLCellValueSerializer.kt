package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.Hyperlink
import io.github.nillerr.ooxml.HyperlinkColumnValue
import java.net.URL

class URLCellValueSerializer : CellValueSerializer {
    override fun serialize(value: Any?): ColumnValue? {
        return when (value) {
            is URL -> HyperlinkColumnValue(Hyperlink(value.toString(), value.toURI()))
            else -> null
        }
    }
}
