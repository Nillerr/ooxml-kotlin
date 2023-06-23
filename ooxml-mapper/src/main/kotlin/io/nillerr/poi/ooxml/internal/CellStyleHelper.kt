package io.nillerr.poi.ooxml.internal

import io.nillerr.poi.ooxml.annotation.CellStyle
import io.nillerr.poi.ooxml.style.HorizontalAlignment
import org.apache.poi.ss.usermodel.CellStyle as ApacheCellStyle

internal object CellStyleHelper {
    fun setHorizontalAlignment(style: ApacheCellStyle, annotations: List<CellStyle>) {
        val annotation = annotations.firstOrNull { it.horizontalAlignment != HorizontalAlignment.UNSPECIFIED }
        if (annotation != null) {
            style.setHorizontalAlignment(annotation.horizontalAlignment)
        }

        style.setHorizontalAlignment(annotations.last().horizontalAlignment)
    }
}
