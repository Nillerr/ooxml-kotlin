package io.github.nillerr.ooxml.table

import io.github.nillerr.ooxml.spreadsheet.ApacheWorkbookFactory
import io.github.nillerr.ooxml.spreadsheet.internal.CellStyleHelper
import io.github.nillerr.ooxml.spreadsheet.internal.FontHelper
import org.apache.poi.common.usermodel.HyperlinkType
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.xssf.streaming.SXSSFCell
import org.apache.poi.xssf.streaming.SXSSFWorkbook
import java.io.OutputStream
import java.time.Clock
import java.time.LocalDate
import java.util.Date

class SXSFFTableEncoder(options: SXSFFTableEncoderOptions) : TableEncoder {
    private val applicationName: String = options.applicationName
    private val applicationVersion: String = options.applicationVersion

    private val clock: Clock = options.clock

    constructor() : this(SXSFFTableEncoderOptions())

    override fun encode(table: Table, output: OutputStream) {
        requireRegularTable(table)

        val factory = ApacheWorkbookFactory(clock, applicationName, applicationVersion)
        val sxWorkbook = factory.createWorkbook()

        val cellStyles = mutableMapOf<io.github.nillerr.ooxml.table.CellStyle, CellStyle>()
        val dataFormats = mutableMapOf<String, Short?>()

        try {
            sxWorkbook.isCompressTempFiles = true

            val sxSheet = table.name?.let { sxWorkbook.createSheet(it) } ?: sxWorkbook.createSheet()

            table.columns.forEachIndexed { colIndex, style ->
                if (style.width > -1) {
                    sxSheet.setColumnWidth(colIndex, style.width * 256)
                }

                if (style.hidden) {
                    sxSheet.setColumnHidden(colIndex, true)
                }
            }

            table.rows.forEachIndexed { rowIndex, row ->
                val sxRow = sxSheet.createRow(rowIndex)

                row.columns.forEachIndexed { colIndex, column ->
                    val sxCell = sxRow.createCell(colIndex)

                    val columnStyle = column.style
                    sxCell.cellStyle = columnStyle?.let { style ->
                        cellStyles.getOrPut(style) {
                            val dataFormatString = style.dataFormat

                            val dataFormat = dataFormats.getOrPut(dataFormatString) {
                                dataFormatString.takeUnless { it.isEmpty() }
                                    ?.let { sxWorkbook.createDataFormat().getFormat(it) }
                            }

                            return@getOrPut sxWorkbook.createCellStyle(style, dataFormat)
                        }
                    }

                    sxCell.setCellValue(column.value)
                }
            }

            // Output
            sxWorkbook.write(output)
        } finally {
            try {
                sxWorkbook.close()
            } finally {
                sxWorkbook.dispose()
            }
        }
    }

    private fun SXSSFWorkbook.createCellStyle(style: io.github.nillerr.ooxml.table.CellStyle, dataFormat: Short?): CellStyle {
        val styles = listOf(style)

        // Style
        val cellStyle = createCellStyle()

        CellStyleHelper.setHorizontalAlignment(cellStyle, styles)

        // Font
        val fonts = styles.map { it.font }
        val cellFont = createFont()

        FontHelper.setSize(cellFont, fonts)
        FontHelper.setWeight(cellFont, fonts)
        FontHelper.setUnderline(cellFont, fonts)
        FontHelper.setColor(cellFont, fonts)

        cellStyle.setFont(cellFont)

        // Data Format
        if (dataFormat != null) {
            cellStyle.dataFormat = dataFormat
        }

        return cellStyle
    }

    private fun SXSSFCell.setCellValue(value: ColumnValue) {
        when (value) {
            EmptyColumnValue -> {
                // Nothing
            }
            is StringColumnValue -> setCellValue(value.value)
            is NumberColumnValue -> setCellValue(value.value.toDouble())
            is BooleanColumnValue -> setCellValue(value.value)
            is InstantColumnValue -> setCellValue(Date.from(value.value))
            is LocalDateColumnValue -> setCellValue(value.value.atStartOfDay())
            is LocalTimeColumnValue -> setCellValue(value.value.atDate(LocalDate.EPOCH))
            is LocalDateTimeColumnValue -> setCellValue(value.value)
            is HyperlinkColumnValue -> {
                val helper = sheet.workbook.creationHelper

                this.hyperlink = helper.createHyperlink(HyperlinkType.URL).apply {
                    address = value.value.uri.toASCIIString()
                }

                setCellValue(value.value.text)
            }
        }
    }
}
