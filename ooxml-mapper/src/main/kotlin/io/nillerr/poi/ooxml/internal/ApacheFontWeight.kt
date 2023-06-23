package io.nillerr.poi.ooxml.internal

import io.nillerr.poi.ooxml.IndexedColor
import io.nillerr.poi.ooxml.style.FontUnderline
import io.nillerr.poi.ooxml.style.FontWeight
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.ss.usermodel.IndexedColors

internal fun Font.setSize(size: Int) {
    if (size > -1) {
        this.fontHeightInPoints = size.toShort()
    }
}

internal fun Font.setWeight(weight: FontWeight) {
    val isBold = weight.isBold()
    if (isBold != null) {
        setBold(isBold)
    }
}

internal fun FontWeight.isBold(): Boolean? {
    return when (this) {
        FontWeight.UNSPECIFIED -> null
        FontWeight.REGULAR -> false
        FontWeight.BOLD -> true
    }
}

internal fun Font.setUnderline(underline: FontUnderline) {
    val apache = underline.toApacheFontUnderline()
    if (apache != null) {
        setUnderline(apache)
    }
}

internal fun FontUnderline.toApacheFontUnderline(): Byte? {
    return when (this) {
        FontUnderline.UNSPECIFIED -> null
        FontUnderline.NONE -> Font.U_NONE
        FontUnderline.SINGLE -> Font.U_SINGLE
        FontUnderline.DOUBLE -> Font.U_DOUBLE
    }
}

internal fun Font.setColor(color: IndexedColor) {
    if (color != IndexedColor.UNSPECIFIED) {
        val apache = IndexedColors.fromInt(color.index)
        setColor(apache.index)
    }
}
