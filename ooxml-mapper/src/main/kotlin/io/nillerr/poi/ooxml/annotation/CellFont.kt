package io.nillerr.poi.ooxml.annotation

import io.nillerr.poi.ooxml.IndexedColor
import io.nillerr.poi.ooxml.style.FontUnderline
import io.nillerr.poi.ooxml.style.FontWeight

annotation class CellFont(
    val size: Int = -1,
    val indexedColor: IndexedColor = IndexedColor.UNSPECIFIED,
    val weight: FontWeight = FontWeight.UNSPECIFIED,
    val underline: FontUnderline = FontUnderline.UNSPECIFIED,
)
