package io.github.nillerr.ooxml.spreadsheet

import io.github.nillerr.ooxml.spreadsheet.annotation.CellStyle
import io.github.nillerr.ooxml.spreadsheet.style.HorizontalAlignment
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
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
    fun `encode to byte array`() {
        // Given
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
            ),
            Expense(
                merchant = "Power",
                description = "Computers",
                count = 1,
                amount = BigDecimal("23000.00"),
                currency = "DKK",
                date = LocalDateTime.parse("2022-06-02T12:21:30"),
                receipt = URI("https://www.power.dk/computere-og-tablets/computere/baerbar-pc/hp-14s-fq2473no-14-baerbar-pc/p-1846428/"),
                hyperlink = Hyperlink("HP Bærbar", URI("https://www.power.dk/computere-og-tablets/computere/baerbar-pc/hp-14s-fq2473no-14-baerbar-pc/p-1846428/")),
            ),
        )

        // When
        val output = encoder.encode(expenses)

        val file = File("output.xlsx")
        file.writeBytes(output)

        // Then
        val workbook = assertIsWorkbook(output)
        assertWorkbookEqualsXMLResource("/output/expected-workbook", workbook)
        assertStylesheetEqualsXMLResource("/output/expected-stylesheet", workbook)

        val sheet = assertSingleSheet(workbook)
        assertSheetEqualsXMLResource("/output/expected-sheet", sheet)
    }

    @Test
    fun `encode to output stream`() {
        // Given
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
            ),
            Expense(
                merchant = "Power",
                description = "Computers",
                count = 1,
                amount = BigDecimal("23000.00"),
                currency = "DKK",
                date = LocalDateTime.parse("2022-06-02T12:21:30"),
                receipt = URI("https://www.power.dk/computere-og-tablets/computere/baerbar-pc/hp-14s-fq2473no-14-baerbar-pc/p-1846428/"),
                hyperlink = Hyperlink("HP Bærbar", URI("https://www.power.dk/computere-og-tablets/computere/baerbar-pc/hp-14s-fq2473no-14-baerbar-pc/p-1846428/")),
            ),
        )

        val output = ByteArrayOutputStream()

        // When
        encoder.encode(expenses, output)

        // Then
        val workbook = assertIsWorkbook(output.toByteArray())
        assertWorkbookEqualsXMLResource("/output/expected-workbook", workbook)
        assertStylesheetEqualsXMLResource("/output/expected-stylesheet", workbook)

        val sheet = assertSingleSheet(workbook)
        assertSheetEqualsXMLResource("/output/expected-sheet", sheet)
    }
}
