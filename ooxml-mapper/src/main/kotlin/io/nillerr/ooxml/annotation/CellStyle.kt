package io.nillerr.ooxml.annotation

import io.nillerr.ooxml.style.HorizontalAlignment

annotation class CellStyle(
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.UNSPECIFIED,
    val font: CellFont = CellFont(),
    val dataFormat: String = ""
)
