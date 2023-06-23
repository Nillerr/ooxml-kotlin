package io.nillerr.poi.ooxml.annotation

import io.nillerr.poi.ooxml.style.HorizontalAlignment

annotation class CellStyle(
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.UNSPECIFIED,
    val font: CellFont = CellFont(),
    val dataFormat: String = ""
)
