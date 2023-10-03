package io.github.nillerr.ooxml.internal

import io.github.nillerr.ooxml.table.ColumnStyle
import org.apache.poi.xssf.streaming.SXSSFSheet

internal fun SXSSFSheet.setColumnWidth(colNum: Int, style: ColumnStyle) {
    if (style.width > -1) {
        setColumnWidth(colNum, style.width * 256)
    }
}

internal fun SXSSFSheet.setColumnStyle(colNum: Int, style: ColumnStyle) {
    setColumnWidth(colNum, style)
    setColumnHidden(colNum, style.hidden)
}
