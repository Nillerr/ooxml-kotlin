package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.Hyperlink
import io.github.nillerr.ooxml.spreadsheet.Expense
import io.github.nillerr.ooxml.spreadsheet.LocalizedColumnNameResolver
import io.github.nillerr.ooxml.spreadsheet.assertEqualsContentsOfResource
import io.github.nillerr.ooxml.table.Tables
import io.github.nillerr.ooxml.table.csv.CSVTableEncoder
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.math.BigDecimal
import java.net.URI
import java.time.LocalDateTime
import kotlin.test.assertEquals

class ObjectTableMapperTests {
    val encoder = CSVTableEncoder()

    val expenses = listOf(
        Expense(
            merchant = "IKEA",
            description = "Office Furniture",
            count = 6,
            amount = BigDecimal("1425.00"),
            currency = "DKK",
            date = LocalDateTime.parse("2022-06-07T23:21:30"),
            receipt = URI("https://www.ikea.com/dk/da/p/lagkapten-alex-skrivebord-turkisgra-sort-s19523513/"),
            hyperlink = Hyperlink(
                "LAGKAPTEN / ALEX Skrivebord, turkisgrå/sort, 140x60 cm - IKEA",
                URI("https://www.ikea.com/dk/da/p/lagkapten-alex-skrivebord-turkisgra-sort-s19523513/")
            ),
        ),
        Expense(
            merchant = "Power",
            description = "Computers",
            count = 1,
            amount = BigDecimal("23000.00"),
            currency = "DKK",
            date = LocalDateTime.parse("2022-06-02T12:21:30"),
            receipt = URI("https://www.power.dk/computere-og-tablets/computere/baerbar-pc/hp-14s-fq2473no-14-baerbar-pc/p-1846428/"),
            hyperlink = Hyperlink(
                "HP Bærbar",
                URI("https://www.power.dk/computere-og-tablets/computere/baerbar-pc/hp-14s-fq2473no-14-baerbar-pc/p-1846428/")
            ),
        ),
    )

    @Test
    fun test() {
        // Given
        val mapper = ObjectTableMapper()

        // When
        val result = mapper.toTable(expenses)

        // Then
        assertEquals(Tables.mapped, result)
    }

    @Test
    fun localized() {
        // Given
        val options = ObjectTableMapperOptions()
        options.columnNameResolver = LocalizedColumnNameResolver()

        val mapper = ObjectTableMapper(options)
        val output = ByteArrayOutputStream()

        val table = mapper.toTable(expenses)

        // When
        encoder.encode(table, output)

        // Then
        assertEqualsContentsOfResource("/table/localized.csv", output)
    }

    @Test
    fun encode() {
        // Given
        val table = Tables.mapped
        val output = ByteArrayOutputStream()

        // When
        encoder.encode(table, output)

        // Then
        assertEqualsContentsOfResource("/table/mapped.csv", output)
    }
}
