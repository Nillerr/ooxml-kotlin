package io.github.nillerr.ooxml.table

import io.github.nillerr.ooxml.spreadsheet.style.HorizontalAlignment

/**
 * Represents the style of a cell in a spreadsheet.
 *
 * @property horizontalAlignment The horizontal alignment of the cell content.
 * @property font The font used for the cell.
 * @property dataFormat The data format used for the cell.
 */
data class CellStyle(
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.UNSPECIFIED,
    val font: CellFont = CellFont(),
    val dataFormat: String = "",
)
