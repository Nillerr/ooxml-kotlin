package io.github.nillerr.ooxml.spreadsheet.annotation

import io.github.nillerr.ooxml.spreadsheet.style.HorizontalAlignment

annotation class CellStyle(
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.UNSPECIFIED,
    val font: CellFont = CellFont(),
    val dataFormat: String = ""
)
