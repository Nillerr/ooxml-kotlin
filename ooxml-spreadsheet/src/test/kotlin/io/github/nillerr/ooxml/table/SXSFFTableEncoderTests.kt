package io.github.nillerr.ooxml.table

import io.github.nillerr.ooxml.spreadsheet.assertIsWorkbook
import io.github.nillerr.ooxml.spreadsheet.assertSheetEqualsXMLResource
import io.github.nillerr.ooxml.spreadsheet.assertSingleSheet
import io.github.nillerr.ooxml.spreadsheet.assertStylesheetEqualsXMLResource
import io.github.nillerr.ooxml.spreadsheet.assertWorkbookEqualsXMLResource
import io.github.nillerr.ooxml.spreadsheet.style.FontWeight
import io.github.nillerr.ooxml.spreadsheet.style.HorizontalAlignment
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SXSFFTableEncoderTests {
    val encoder = SXSFFTableEncoder()

    @Test
    fun irregularTableThrowsException() {
        // Given
        val irregularTable = Table(
            rows = listOf(
                Row(
                    Column(value = StringColumnValue("A")),
                    Column(value = NumberColumnValue(9)),
                ),
                Row(
                    Column(value = NumberColumnValue(9)),
                ),
            )
        )

        val outputStream = ByteArrayOutputStream()

        // When
        val exception = assertFailsWith<IllegalArgumentException> {
            encoder.encode(irregularTable, outputStream)
        }

        // Then
        val expectedMessage = "The number of columns `1` on row `1` differs from the number of " +
                "columns `2` of a previous row."
        assertEquals(expectedMessage, exception.message)
    }

    @Test
    fun success() {
        // Given
        val header = CellStyle(
            horizontalAlignment = HorizontalAlignment.CENTER,
            font = CellFont(
                size = 14,
                weight = FontWeight.BOLD,
            )
        )

        val value = CellStyle(font = CellFont(size = 12))
        val date = value.copy(dataFormat = "dd/mm/yyyy")

        val table = Table(
            columns = listOf(
                ColumnStyle(width = 20),
                ColumnStyle(width = 14),
                ColumnStyle(width = 12),
                ColumnStyle(width = 50),
                ColumnStyle(width = 50),
            ),
            rows = listOf(
                Row(
                    Column(value = StringColumnValue("id"), style = header),
                    Column(value = StringColumnValue("date"), style = header),
                    Column(value = StringColumnValue("distance"), style = header),
                    Column(value = StringColumnValue("origin"), style = header),
                    Column(value = StringColumnValue("destination"), style = header),
                ),
                Row(
                    Column(value = StringColumnValue("mileage-1"), style = value),
                    Column(value = LocalDateColumnValue(LocalDate.parse("2022-03-06")), style = date),
                    Column(value = NumberColumnValue(4), style = value),
                    Column(value = StringColumnValue("Åbrinken 61"), style = value),
                    Column(value = StringColumnValue("Billedeskærevej 17"), style = value),
                ),
                Row(
                    Column(value = StringColumnValue("mileage-2"), style = value),
                    Column(value = LocalDateColumnValue(LocalDate.parse("2022-03-06")), style = date),
                    Column(value = NumberColumnValue(4), style = value),
                    Column(value = StringColumnValue("Billedeskærevej 17"), style = value),
                    Column(value = StringColumnValue("Åbrinken 61"), style = value),
                ),
            ),
        )

        val output = ByteArrayOutputStream()

        // When
        encoder.encode(table, output)

        // Then
        val workbook = assertIsWorkbook(output.toByteArray())
        assertWorkbookEqualsXMLResource("/table/workbook.xml", workbook)
        assertStylesheetEqualsXMLResource("/table/styles.xml", workbook)

        val sheet = assertSingleSheet(workbook)
        assertSheetEqualsXMLResource("/table/sheet1.xml", sheet)
    }
}
