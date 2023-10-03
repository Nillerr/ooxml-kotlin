package io.github.nillerr.ooxml.table.csv

import io.github.nillerr.ooxml.spreadsheet.assertEqualsContentsOfResource
import io.github.nillerr.ooxml.table.Tables
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CSVTableEncoderTests {
    val encoder = CSVTableEncoder()

    @Test
    fun irregularTableThrowsException() {
        // Given
        val table = Tables.irregularTable
        val outputStream = ByteArrayOutputStream()

        // When
        val exception = assertFailsWith<IllegalArgumentException> {
            encoder.encode(table, outputStream)
        }

        // Then
        val expectedMessage = "The number of columns `1` on row `1` differs from the number of " +
                "columns `2` of a previous row."
        assertEquals(expectedMessage, exception.message)
    }

    @Test
    fun success() {
        // Given
        val table = Tables.regular
        val output = ByteArrayOutputStream()

        // When
        encoder.encode(table, output)

        // Then
        assertEqualsContentsOfResource("/table/csv.csv", output)
    }
}
