package io.github.nillerr.ooxml.internal

import io.github.nillerr.ooxml.table.CellFont
import io.github.nillerr.ooxml.table.CellStyle
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.xssf.streaming.SXSSFWorkbook
import org.apache.poi.ss.usermodel.CellStyle as ApacheCellStyle

internal fun SXSSFWorkbook.createCellStyle(cellStyle: CellStyle, dataFormat: Short?): ApacheCellStyle {
    // Style
    val style = createCellStyle(cellStyle)

    // Font
    val font = createFont(cellStyle.font)
    style.setFont(font)

    // Data Format
    style.setDataFormat(dataFormat)

    return style
}

internal fun SXSSFWorkbook.createFont(cellFont: CellFont): Font {
    val font = createFont()
    font.setSize(cellFont.size)
    font.setWeight(cellFont.weight)
    font.setUnderline(cellFont.underline)
    font.setColor(cellFont.indexedColor)
    return font
}

internal fun SXSSFWorkbook.createCellStyle(cellStyle: CellStyle): ApacheCellStyle {
    val style = createCellStyle()
    style.setHorizontalAlignment(cellStyle.horizontalAlignment)
    return style
}
