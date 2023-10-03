package io.github.nillerr.ooxml.internal

import io.github.nillerr.ooxml.style.HorizontalAlignment
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.HorizontalAlignment as ApacheHorizontalAlignment

internal fun CellStyle.setHorizontalAlignment(horizontalAlignment: HorizontalAlignment) {
    when (horizontalAlignment) {
        HorizontalAlignment.UNSPECIFIED -> {
            // Nothing
        }
        HorizontalAlignment.GENERAL -> alignment = ApacheHorizontalAlignment.GENERAL
        HorizontalAlignment.LEFT -> alignment = ApacheHorizontalAlignment.LEFT
        HorizontalAlignment.CENTER -> alignment = ApacheHorizontalAlignment.CENTER
        HorizontalAlignment.RIGHT -> alignment = ApacheHorizontalAlignment.RIGHT
    }
}

internal fun CellStyle.setDataFormat(dataFormat: Short?) {
    if (dataFormat != null) {
        this.dataFormat = dataFormat
    }
}
