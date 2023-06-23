package io.nillerr.poi.ooxml.internal

import io.nillerr.poi.ooxml.CellSerializerRegistry
import io.nillerr.poi.ooxml.ColumnNameResolver
import io.nillerr.poi.ooxml.TypeInfo
import io.nillerr.poi.ooxml.annotation.CellStyle
import org.apache.poi.xssf.streaming.SXSSFSheet
import org.apache.poi.ss.usermodel.CellStyle as ApacheCellStyle

internal class SheetHelper(
    private val sheet: SXSSFSheet,
    private val defaultCellStyle: CellStyle,
    private val defaultHeaderStyle: CellStyle,
    private val serializers: CellSerializerRegistry,
    private val workbookHelper: WorkbookHelper,
    private val columnNameResolver: ColumnNameResolver,
) {
    fun createHead(typeInfo: TypeInfo) {
        val headerRow = sheet.createRow(0)

        typeInfo.parameterNames.forEachIndexed { colNum, parameterName ->
            // Header
            val cell = headerRow.createCell(colNum)

            val columnInfo = typeInfo.getColumn(parameterName)

            val annotation = columnInfo.annotation
            sheet.setColumnWidth(colNum, annotation)

            val columnName = columnNameResolver.getName(columnInfo)
            cell.setCellValue(columnName)

            // Header Style
            val headerStyles = listOf(annotation.headerStyle, typeInfo.sheet.headerStyle, defaultHeaderStyle)
            val headerCellStyle = workbookHelper.createCellStyle(headerStyles, columnInfo)
            cell.cellStyle = headerCellStyle
        }
    }

    fun <T : Any> createBody(data: List<T>, typeInfo: TypeInfo) {
        val valueCellStyles = createValueCellStyles(typeInfo)

        data.forEachIndexed { rowNum, rowData ->
            val row = this.sheet.createRow(rowNum + 1)

            typeInfo.parameterNames.forEachIndexed { colNum, parameterName ->
                // Value
                val cell = row.createCell(colNum)
                cell.cellStyle = valueCellStyles[parameterName]!!

                val property = typeInfo.getProperty(parameterName)
                val value = property.get(rowData)
                if (value == null) {
                    cell.setBlank()
                } else {
                    val serializer = serializers.serializer(property.returnType)
                    val writer = ApacheCellWriter(cell)
                    serializer.serialize(value, writer, serializers)
                }
            }
        }
    }

    private fun createValueCellStyles(typeInfo: TypeInfo): Map<String, ApacheCellStyle> {
        val valueCellStyles = mutableMapOf<String, ApacheCellStyle>()

        typeInfo.parameterNames.forEach { parameterName ->
            val columnInfo = typeInfo.getColumn(parameterName)

            val annotation = columnInfo.annotation

            // Value Style
            val valueStyles = listOf(annotation.style, typeInfo.sheet.valueStyle, defaultCellStyle)
            val valueCellStyle = workbookHelper.createCellStyle(valueStyles, columnInfo)
            valueCellStyles[parameterName] = valueCellStyle
        }

        return valueCellStyles
    }
}
