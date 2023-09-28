package io.github.nillerr.ooxml.table

import io.github.nillerr.ooxml.spreadsheet.style.HorizontalAlignment

data class ColumnStyle(
    val width: Int = -1,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.UNSPECIFIED,
    val font: ColumnFont = ColumnFont(),
    val dataFormat: String = "",
)
