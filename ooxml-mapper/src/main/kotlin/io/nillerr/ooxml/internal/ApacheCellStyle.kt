package io.nillerr.ooxml.internal

import io.nillerr.ooxml.style.HorizontalAlignment
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.xssf.usermodel.XSSFColor
import org.apache.poi.ss.usermodel.HorizontalAlignment as ApacheHorizontalAlignment

internal fun CellStyle.setHorizontalAlignment(horizontalAlignment: HorizontalAlignment) {
    when (horizontalAlignment) {
        HorizontalAlignment.UNSPECIFIED -> {}
        HorizontalAlignment.GENERAL -> this.alignment = ApacheHorizontalAlignment.GENERAL
        HorizontalAlignment.LEFT -> this.alignment = ApacheHorizontalAlignment.LEFT
        HorizontalAlignment.CENTER -> this.alignment = ApacheHorizontalAlignment.CENTER
        HorizontalAlignment.RIGHT -> this.alignment = ApacheHorizontalAlignment.RIGHT
    }
}
