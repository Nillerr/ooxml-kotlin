package io.nillerr.poi.ooxml.internal

import io.nillerr.poi.ooxml.CellWriter
import io.nillerr.poi.ooxml.Hyperlink
import org.apache.poi.common.usermodel.HyperlinkType
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.CellType
import java.net.URI
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.util.*

internal class ApacheCellWriter(private val cell: Cell) : CellWriter {
    override fun writeString(value: String) {
        cell.setCellValue(value)
    }

    override fun writeNumber(value: Number) {
        cell.setCellValue(value.toDouble())
    }

    override fun writeBoolean(value: Boolean) {
        cell.setCellValue(value)
    }

    override fun writeInstant(value: Instant) {
        cell.setCellValue(Date.from(value))
    }

    override fun writeLocalDate(value: LocalDate) {
        cell.setCellValue(value.atStartOfDay())
    }

    override fun writeLocalTime(value: LocalTime) {
        cell.setCellValue(value.atDate(LocalDate.EPOCH))
    }

    override fun writeLocalDateTime(value: LocalDateTime) {
        cell.setCellValue(value)
    }

    override fun writeHyperlink(value: Hyperlink) {
        val helper = cell.sheet.workbook.creationHelper

        val hyperlink = helper.createHyperlink(HyperlinkType.URL)
        hyperlink.address = value.uri.toASCIIString()
        cell.hyperlink = hyperlink

        cell.setCellValue(value.text)
    }
}
