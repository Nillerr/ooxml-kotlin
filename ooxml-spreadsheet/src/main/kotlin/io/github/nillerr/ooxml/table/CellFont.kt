package io.github.nillerr.ooxml.table

import io.github.nillerr.ooxml.IndexedColor
import io.github.nillerr.ooxml.style.FontUnderline
import io.github.nillerr.ooxml.style.FontWeight

data class CellFont(
    val size: Int = -1,
    val indexedColor: IndexedColor = IndexedColor.UNSPECIFIED,
    val weight: FontWeight = FontWeight.UNSPECIFIED,
    val underline: FontUnderline = FontUnderline.UNSPECIFIED,
)
