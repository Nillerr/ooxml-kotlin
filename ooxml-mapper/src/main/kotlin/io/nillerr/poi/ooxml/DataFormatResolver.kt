package io.nillerr.poi.ooxml

import io.nillerr.poi.ooxml.annotation.CellStyle

interface DataFormatResolver {
    fun getDataFormat(styles: List<CellStyle>, columnInfo: ColumnInfo): String?
}
