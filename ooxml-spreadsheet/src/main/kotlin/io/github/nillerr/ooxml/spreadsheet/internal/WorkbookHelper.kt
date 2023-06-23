package io.github.nillerr.ooxml.spreadsheet.internal

import io.github.nillerr.ooxml.spreadsheet.ColumnInfo
import io.github.nillerr.ooxml.spreadsheet.DataFormatResolver
import io.github.nillerr.ooxml.spreadsheet.annotation.CellStyle
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFColor
import org.apache.poi.ss.usermodel.CellStyle as ApacheCellStyle

internal class WorkbookHelper(
    private val workbook: Workbook,
    private val dataFormatResolver: DataFormatResolver,
) {
    fun createCellStyle(styles: List<CellStyle>, columnInfo: ColumnInfo): ApacheCellStyle {
        // Style
        val cellStyle = workbook.createCellStyle()

        CellStyleHelper.setHorizontalAlignment(cellStyle, styles)

        // Font
        val fonts = styles.map { it.font }
        val cellFont = workbook.createFont()

        FontHelper.setSize(cellFont, fonts)
        FontHelper.setWeight(cellFont, fonts)
        FontHelper.setUnderline(cellFont, fonts)
        FontHelper.setColor(cellFont, fonts)

        cellStyle.setFont(cellFont)

        // Data Format
        setDataFormat(cellStyle, styles, columnInfo, dataFormatResolver)

        return cellStyle
    }

    fun setDataFormat(style: ApacheCellStyle, annotations: List<CellStyle>, columnInfo: ColumnInfo, resolver: DataFormatResolver) {
        val dataFormat = resolver.getDataFormat(annotations, columnInfo)
        if (dataFormat != null) {
            style.dataFormat = workbook.createDataFormat().getFormat(dataFormat)
        }
    }
}
