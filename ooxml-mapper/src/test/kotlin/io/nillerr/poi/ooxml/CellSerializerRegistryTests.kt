package io.nillerr.poi.ooxml

import org.junit.jupiter.api.Test
import kotlin.reflect.typeOf
import kotlin.test.assertEquals
import kotlin.test.assertIs

class CellSerializerRegistryTests {
    @Test
    fun `getSerializer throws WorkbookEncodingException when serializer for type is not registered`() {
        // Given
        val registry = CellSerializerRegistry()

        // When
        val result = runCatching { registry.serializer(typeOf<Expense>()) }

        // Then
        val exception = assertIs<WorkbookEncodingException>(result.exceptionOrNull())
        assertEquals("A serializer for the type `io.nillerr.poi.ooxml.Expense` could not be found.", exception.message)
    }
}
