package io.nillerr.poi.ooxml

import io.nillerr.poi.ooxml.internal.TypeHelper
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import kotlin.test.assertEquals

class DefaultSheetNameResolverTests {
    @Test
    fun `getSheetName returns simple name of type`() {
        // Given
        val resolver = DefaultSheetNameResolver()
        val typeInfo = TypeHelper.createTypeInfo(ImplicitEntity::class)

        // When
        val result = resolver.getSheetName(typeInfo)

        // Then
        assertEquals("ImplicitEntity", result)
    }
}
