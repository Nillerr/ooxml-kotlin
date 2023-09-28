package io.github.nillerr.ooxml.table

import io.github.nillerr.ooxml.spreadsheet.IndexedColor
import io.github.nillerr.ooxml.spreadsheet.style.FontUnderline
import io.github.nillerr.ooxml.spreadsheet.style.FontWeight

data class ColumnFont(
    val size: Int = -1,
    val indexedColor: IndexedColor = IndexedColor.UNSPECIFIED,
    val weight: FontWeight = FontWeight.UNSPECIFIED,
    val underline: FontUnderline = FontUnderline.UNSPECIFIED,
)
