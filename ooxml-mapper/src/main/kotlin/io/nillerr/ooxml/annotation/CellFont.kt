package io.nillerr.ooxml.annotation

import io.nillerr.ooxml.IndexedColor
import io.nillerr.ooxml.style.FontUnderline
import io.nillerr.ooxml.style.FontWeight

annotation class CellFont(
    val size: Int = -1,
    val indexedColor: IndexedColor = IndexedColor.UNSPECIFIED,
    val weight: FontWeight = FontWeight.UNSPECIFIED,
    val underline: FontUnderline = FontUnderline.UNSPECIFIED,
)
