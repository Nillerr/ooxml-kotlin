package io.nillerr.ooxml

import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.ByteArrayInputStream
import java.io.InputStream
import kotlin.test.assertEquals

fun assertIsWorkbook(input: ByteArray): XSSFWorkbook {
    return assertIsWorkbook(ByteArrayInputStream(input))
}

fun assertIsWorkbook(input: InputStream): XSSFWorkbook {
    return XSSFWorkbook(input)
}

fun assertWorkbookEqualsXMLResource(name: String, workbook: XSSFWorkbook) {
    val expected = readTestResourceAsString(name)
    assertWorkbookEqualsXML(expected, workbook)
}

fun assertWorkbookEqualsXML(expected: String, workbook: XSSFWorkbook) {
    val actual = workbook.ctWorkbook.toString()
    assertEquals(expected.trim(), actual.trim())
}

fun assertStylesheetEqualsXMLResource(name: String, workbook: XSSFWorkbook) {
    val expected = readTestResourceAsString(name)
    assertStylesEqualsXML(expected, workbook)
}

fun assertStylesEqualsXML(expected: String, workbook: XSSFWorkbook) {
    val actual = workbook.stylesSource.ctStylesheet.toString()
    assertEquals(expected.trim(), actual.trim())
}

fun assertSingleSheet(workbook: XSSFWorkbook): XSSFSheet {
    assertEquals(1, workbook.numberOfSheets)
    return workbook.getSheetAt(0)
}

fun assertSheetEqualsXMLResource(name: String, sheet: XSSFSheet) {
    val expected = readTestResourceAsString(name)
    assertSheetEqualsXML(expected, sheet)
}

fun assertSheetEqualsXML(expected: String, sheet: XSSFSheet) {
    val actual = sheet.ctWorksheet.toString()
    assertEquals(expected.trim(), actual.trim())
}
