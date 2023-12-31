package io.github.nillerr.ooxml.spreadsheet

import io.github.nillerr.ooxml.Hyperlink
import io.github.nillerr.ooxml.IndexedColor
import io.github.nillerr.ooxml.databind.annotations.CellFont
import io.github.nillerr.ooxml.databind.annotations.CellStyle
import io.github.nillerr.ooxml.databind.annotations.Column
import io.github.nillerr.ooxml.style.FontUnderline
import io.github.nillerr.ooxml.style.FontWeight
import io.github.nillerr.ooxml.style.HorizontalAlignment
import java.math.BigDecimal
import java.net.URI
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Expense(
    @Column("expenses.column.merchant", width = 20)
    val merchant: String,

    @Column("expenses.column.description", width = 50)
    val description: String,

    @Column("expenses.column.count", width = 6)
    val count: Int,

    @Column("expenses.column.amount", width = 12)
    val amount: BigDecimal,

    @Column("expenses.column.currency", width = 10)
    val currency: String,

    @Column("expenses.column.date", width = 14)
    val date: LocalDateTime,

    @Column("expenses.column.receipt", width = 100)
    val receipt: URI,

    @Column("URL")
    val hyperlink: Hyperlink,

    @Column
    val blank: Double? = null,

    @Column
    val double: Double = 5.52,

    @Column
    val boolean: Boolean = true,

    @Column
    val instant: Instant = Instant.parse("2023-03-01T23:04:12Z"),

    @Column(
        style = CellStyle(
            horizontalAlignment = HorizontalAlignment.LEFT,
            font = CellFont(
                underline = FontUnderline.DOUBLE,
            )
        )
    )
    val localDate: LocalDate = LocalDate.parse("2022-01-23"),

    @Column(
        style = CellStyle(
            horizontalAlignment = HorizontalAlignment.RIGHT,
            font = CellFont(
                weight = FontWeight.REGULAR,
                underline = FontUnderline.NONE,
            )
        )
    )
    val localTime: LocalTime = LocalTime.parse("11:33:05"),

    @Column(
        style = CellStyle(
            horizontalAlignment = HorizontalAlignment.GENERAL,
            font = CellFont(
                size = 13,
                indexedColor = IndexedColor.RED,
                weight = FontWeight.BOLD,
                underline = FontUnderline.SINGLE,
            ),
            dataFormat = "#",
        )
    )
    val styled: Double = 5.52,
)
