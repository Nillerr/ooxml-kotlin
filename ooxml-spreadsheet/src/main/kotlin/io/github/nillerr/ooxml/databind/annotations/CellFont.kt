package io.github.nillerr.ooxml.databind.annotations

import io.github.nillerr.ooxml.IndexedColor
import io.github.nillerr.ooxml.style.FontUnderline
import io.github.nillerr.ooxml.style.FontWeight

annotation class CellFont(
    val size: Int = -1,
    val indexedColor: IndexedColor = IndexedColor.UNSPECIFIED,
    val weight: FontWeight = FontWeight.UNSPECIFIED,
    val underline: FontUnderline = FontUnderline.UNSPECIFIED,
)

internal fun CellFont.isEqualTo(other: CellFont): Boolean {
    return size == other.size &&
            indexedColor == other.indexedColor &&
            weight == other.weight &&
            underline == other.underline
}
