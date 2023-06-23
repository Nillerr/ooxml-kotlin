package io.github.nillerr.ooxml.spreadsheet

import io.github.nillerr.ooxml.spreadsheet.annotation.CellStyle

interface DataFormatResolver {
    fun getDataFormat(styles: List<CellStyle>, columnInfo: ColumnInfo): String?
}
