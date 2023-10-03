package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.Hyperlink
import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.HyperlinkColumnValue
import java.net.URI

class URICellValueSerializer : CellValueSerializer {
    override fun serialize(value: Any?): ColumnValue? {
        return when (value) {
            is URI -> HyperlinkColumnValue(Hyperlink(value.toString(), value))
            else -> null
        }
    }
}
