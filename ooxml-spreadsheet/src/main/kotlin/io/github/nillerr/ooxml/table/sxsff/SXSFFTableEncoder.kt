package io.github.nillerr.ooxml.table.sxsff

import io.github.nillerr.ooxml.BooleanColumnValue
import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.EmptyColumnValue
import io.github.nillerr.ooxml.HyperlinkColumnValue
import io.github.nillerr.ooxml.InstantColumnValue
import io.github.nillerr.ooxml.LocalDateColumnValue
import io.github.nillerr.ooxml.LocalDateTimeColumnValue
import io.github.nillerr.ooxml.LocalTimeColumnValue
import io.github.nillerr.ooxml.NumberColumnValue
import io.github.nillerr.ooxml.StringColumnValue
import io.github.nillerr.ooxml.internal.createCellStyle
import io.github.nillerr.ooxml.internal.setColumnStyle
import io.github.nillerr.ooxml.table.CellStyle
import io.github.nillerr.ooxml.table.Table
import io.github.nillerr.ooxml.table.TableEncoder
import io.github.nillerr.ooxml.table.TableEncodingException
import io.github.nillerr.ooxml.table.requireRegularTable
import org.apache.poi.common.usermodel.HyperlinkType
import org.apache.poi.xssf.streaming.SXSSFCell
import org.apache.poi.xssf.streaming.SXSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.OutputStream
import java.lang.Exception
import java.time.Clock
import java.time.LocalDate
import java.util.Date
import java.util.Optional

class SXSFFTableEncoder(options: SXSFFTableEncoderOptions) : TableEncoder {
    private val applicationName: String = options.applicationName
    private val applicationVersion: String = options.applicationVersion

    private val clock: Clock = options.clock

    constructor() : this(SXSFFTableEncoderOptions())

    override fun encode(table: Table, output: OutputStream) {
        requireRegularTable(table)

        val xss = XSSFWorkbook()

        val props = xss.properties

        val core = props.coreProperties
        val now = clock.instant()
        core.setModified(Optional.of(Date.from(now)))

        val extendedProperties = props.extendedProperties
        extendedProperties.application = applicationName
        extendedProperties.appVersion = applicationVersion

        val sxWorkbook = SXSSFWorkbook(xss)

        val cellStyles = mutableMapOf<CellStyle, org.apache.poi.ss.usermodel.CellStyle>()
        val dataFormats = mutableMapOf<String, Short?>()

        try {
            sxWorkbook.isCompressTempFiles = true

            val sxSheet = table.name?.let { sxWorkbook.createSheet(it) } ?: sxWorkbook.createSheet()

            table.columns.forEachIndexed { colIndex, style ->
                sxSheet.setColumnStyle(colIndex, style)
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
        } catch (e: Exception) {
            throw TableEncodingException("An exception occurred while encoding the table", e)
        } finally {
            try {
                sxWorkbook.close()
            } finally {
                sxWorkbook.dispose()
            }
        }
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
            is LocalTimeColumnValue -> setCellValue(value.value.atDate(LocalDate.ofEpochDay(0)))
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
