package io.nillerr.poi.ooxml.internal

import io.nillerr.poi.ooxml.annotation.Column
import org.apache.poi.xssf.streaming.SXSSFSheet

internal fun SXSSFSheet.setColumnWidth(colNum: Int, annotation: Column) {
    if (annotation.width > -1) {
        setColumnWidth(colNum, annotation.width * 256)
    }
}
