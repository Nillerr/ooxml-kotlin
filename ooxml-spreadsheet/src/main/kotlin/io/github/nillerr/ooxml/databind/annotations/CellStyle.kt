package io.github.nillerr.ooxml.databind.annotations

import io.github.nillerr.ooxml.style.HorizontalAlignment

annotation class CellStyle(
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.UNSPECIFIED,
    val font: CellFont = CellFont(),
    val dataFormat: String = ""
)

internal fun CellStyle.isEqualTo(other: CellStyle): Boolean {
    return horizontalAlignment == other.horizontalAlignment
            && font.isEqualTo(other.font)
            && dataFormat == other.dataFormat
}
