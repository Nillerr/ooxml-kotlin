package io.github.nillerr.ooxml.spreadsheet.internal

import io.github.nillerr.ooxml.spreadsheet.annotation.CellStyle
import io.github.nillerr.ooxml.spreadsheet.style.HorizontalAlignment
import io.github.nillerr.ooxml.table.ColumnStyle
import org.apache.poi.ss.usermodel.CellStyle as ApacheCellStyle

internal object CellStyleHelper {
    fun setHorizontalAlignment(style: ApacheCellStyle, annotations: List<CellStyle>) {
        val annotation = annotations.firstOrNull { it.horizontalAlignment != HorizontalAlignment.UNSPECIFIED }
        if (annotation != null) {
            style.setHorizontalAlignment(annotation.horizontalAlignment)
        }

        style.setHorizontalAlignment(annotations.last().horizontalAlignment)
    }

    @JvmName("setHorizontalAlignmentForColumnStyles")
    fun setHorizontalAlignment(style: ApacheCellStyle, annotations: List<ColumnStyle>) {
        val annotation = annotations.firstOrNull { it.horizontalAlignment != HorizontalAlignment.UNSPECIFIED }
        if (annotation != null) {
            style.setHorizontalAlignment(annotation.horizontalAlignment)
        }

        style.setHorizontalAlignment(annotations.last().horizontalAlignment)
    }
}
