package io.github.nillerr.ooxml.spreadsheet

import io.github.nillerr.ooxml.spreadsheet.annotation.CellStyle
import io.github.nillerr.ooxml.spreadsheet.internal.SheetHelper
import io.github.nillerr.ooxml.spreadsheet.internal.WorkbookHelper
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.time.Clock
import kotlin.reflect.KClass

class WorkbookEncoder {
    var sheetNameResolver: SheetNameResolver = DefaultSheetNameResolver()
    var columnNameResolver: ColumnNameResolver =
        DefaultColumnNameResolver()
    var dataFormatResolver: DataFormatResolver = DefaultDataFormatResolver()

    var applicationName: String = "io.nillerr.ooxml:spreadsheet"
    var applicationVersion: String = "1.0.0"

    var typeRegistry = TypeRegistry()
    var serializers = CellSerializerRegistry()

    var defaultHeaderStyle = CellStyle()
    var defaultCellStyle = CellStyle()

    var clock: Clock = Clock.systemDefaultZone()

    inline fun <reified T : Any> encode(data: List<T>): ByteArray {
        return encode(data, T::class)
    }

    fun <T : Any> encode(data: List<T>, type: KClass<T>): ByteArray {
        val output = ByteArrayOutputStream()
        encode(data, type, output)
        return output.toByteArray()
    }

    inline fun <reified T : Any> encode(data: List<T>, output: OutputStream) {
        return encode(data, T::class, output)
    }

    fun <T : Any> encode(data: List<T>, type: KClass<T>, output: OutputStream) {
        val typeInfo = typeRegistry.getTypeInfo(type)

        val workbookFactory =
            ApacheWorkbookFactory(clock, applicationName, applicationVersion)
        val workbook = workbookFactory.createWorkbook()

        try {
            workbook.isCompressTempFiles = true

            val workbookHelper = WorkbookHelper(workbook, dataFormatResolver)

            // Sheet
            val sheetName = sheetNameResolver.getSheetName(typeInfo)
            val wbSheet = workbook.createSheet(sheetName)
            val sheetHelper = SheetHelper(wbSheet, defaultCellStyle, defaultHeaderStyle, serializers, workbookHelper, columnNameResolver)

            // Header
            sheetHelper.createHead(typeInfo)

            // Body
            sheetHelper.createBody(data, typeInfo)

            workbook.write(output)
        } finally {
            workbook.close()
            workbook.dispose()
        }
    }
}
