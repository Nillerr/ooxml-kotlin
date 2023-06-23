package io.nillerr.ooxml.internal

import io.nillerr.ooxml.IndexedColor
import io.nillerr.ooxml.annotation.CellFont
import io.nillerr.ooxml.style.FontUnderline
import io.nillerr.ooxml.style.FontWeight
import org.apache.poi.ss.usermodel.Font

internal object FontHelper {
    fun setSize(font: Font, annotations: List<CellFont>) {
        val annotation = annotations.firstOrNull { it.size > -1 }
        if (annotation != null) {
            font.setSize(annotation.size)
        }

        font.setSize(annotations.last().size)
    }

    fun setWeight(font: Font, annotations: List<CellFont>) {
        val annotation = annotations.firstOrNull { it.weight != FontWeight.UNSPECIFIED }
        if (annotation != null) {
            font.setWeight(annotation.weight)
        }

        font.setWeight(annotations.last().weight)
    }

    fun setUnderline(font: Font, annotations: List<CellFont>) {
        val annotation = annotations.firstOrNull { it.underline != FontUnderline.UNSPECIFIED }
        if (annotation != null) {
            font.setUnderline(annotation.underline)
        }

        font.setUnderline(annotations.last().underline)
    }

    fun setColor(font: Font, annotations: List<CellFont>) {
        val annotation = annotations.firstOrNull { it.indexedColor != IndexedColor.UNSPECIFIED }
        if (annotation != null) {
            font.setColor(annotation.indexedColor)
        }

        font.setColor(annotations.last().indexedColor)
    }
}
