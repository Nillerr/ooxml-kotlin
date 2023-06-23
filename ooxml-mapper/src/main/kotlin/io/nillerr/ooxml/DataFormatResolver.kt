package io.nillerr.ooxml

import io.nillerr.ooxml.annotation.CellStyle

interface DataFormatResolver {
    fun getDataFormat(styles: List<CellStyle>, columnInfo: ColumnInfo): String?
}
