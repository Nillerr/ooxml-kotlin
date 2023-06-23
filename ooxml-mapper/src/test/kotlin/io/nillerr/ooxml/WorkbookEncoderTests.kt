package io.nillerr.ooxml

import io.nillerr.ooxml.annotation.CellStyle
import io.nillerr.ooxml.style.HorizontalAlignment
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.math.BigDecimal
import java.net.URI
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class WorkbookEncoderTests {
    // Mocks
    private val clock = Clock.fixed(Instant.parse("2023-03-06T12:05:23Z"), ZoneId.of("UTC"))

    // SUT
    private val encoder = WorkbookEncoder()

    @BeforeEach
    fun beforeEach() {
        encoder.clock = clock
        encoder.columnNameResolver = LocalizedColumnNameResolver()
        encoder.defaultHeaderStyle = CellStyle(
            horizontalAlignment = HorizontalAlignment.CENTER,
        )
    }

    @Test
    fun foo() {
        // Given
        val expectedWorkbookXML = readTestResourceAsString("/output/expected-workbook")
        val expectedStylesheetXML = readTestResourceAsString("/output/expected-stylesheet")
        val expectedSheetXML = readTestResourceAsString("/output/expected-sheet")

        val expenses = listOf(
            Expense(
                merchant = "IKEA",
                description = "Office Furniture",
                count = 6,
                amount = BigDecimal("1425.00"),
                currency = "DKK",
                date = LocalDateTime.parse("2022-06-07T23:21:30"),
                receipt = URI("https://www.ikea.com/dk/da/p/lagkapten-alex-skrivebord-turkisgra-sort-s19523513/"),
                hyperlink = Hyperlink("LAGKAPTEN / ALEX Skrivebord, turkisgrå/sort, 140x60 cm - IKEA", URI("https://www.ikea.com/dk/da/p/lagkapten-alex-skrivebord-turkisgra-sort-s19523513/")),
            )
        )

        // When
        val output = encoder.encode(expenses)

        val file = File("output.xlsx")
        file.writeBytes(output)

        // Then
        val workbook = assertIsWorkbook(output)
        assertWorkbookEqualsXML(expectedWorkbookXML, workbook)
        assertStylesEqualsXML(expectedStylesheetXML, workbook)

        val sheet = assertSingleSheet(workbook)
        assertSheetEqualsXML(expectedSheetXML, sheet)
    }
}
