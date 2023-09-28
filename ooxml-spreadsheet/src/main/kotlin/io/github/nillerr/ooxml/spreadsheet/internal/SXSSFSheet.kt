package io.github.nillerr.ooxml.spreadsheet.internal

import io.github.nillerr.ooxml.spreadsheet.annotation.Column
import io.github.nillerr.ooxml.table.ColumnStyle
import org.apache.poi.xssf.streaming.SXSSFSheet

internal fun SXSSFSheet.setColumnWidth(colNum: Int, annotation: Column) {
    if (annotation.width > -1) {
        setColumnWidth(colNum, annotation.width * 256)
    }
}

internal fun SXSSFSheet.setColumnWidth(colNum: Int, style: ColumnStyle) {
    if (style.width > -1) {
        setColumnWidth(colNum, style.width * 256)
    }
}
