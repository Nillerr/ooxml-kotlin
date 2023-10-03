package io.github.nillerr.ooxml.table

import io.github.nillerr.ooxml.EmptyColumnValue
import io.github.nillerr.ooxml.Hyperlink
import io.github.nillerr.ooxml.IndexedColor
import io.github.nillerr.ooxml.LocalDateTimeColumnValue
import io.github.nillerr.ooxml.NumberColumnValue
import io.github.nillerr.ooxml.style.FontUnderline
import io.github.nillerr.ooxml.style.FontWeight
import io.github.nillerr.ooxml.style.HorizontalAlignment
import java.math.BigDecimal
import java.net.URI
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object Tables {
    val irregularTable = Table(
        rows = listOf(
            Row(
                Column(value = "A"),
                Column(value = 9),
            ),
            Row(
                Column(value = 9),
            ),
        )
    )

    val header = CellStyle(
        horizontalAlignment = HorizontalAlignment.CENTER,
        font = CellFont(
            size = 14,
            weight = FontWeight.BOLD,
        )
    )

    val value = CellStyle(font = CellFont(size = 12))
    val date = value.copy(dataFormat = "dd/mm/yyyy")

    val regular = Table(
        columns = listOf(
            ColumnStyle(width = 20),
            ColumnStyle(width = 14),
            ColumnStyle(width = 12),
            ColumnStyle(width = 50),
            ColumnStyle(width = 50),
        ),
        rows = listOf(
            Row(
                Column(value = "id", style = header),
                Column(value = "date", style = header),
                Column(value = "distance", style = header),
                Column(value = "origin", style = header),
                Column(value = "destination", style = header),
            ),
            Row(
                Column(value = "mileage-1", style = value),
                Column(value = LocalDate.parse("2022-03-06"), style = date),
                Column(value = 4, style = value),
                Column(value = "Åbrinken 61", style = value),
                Column(value = "Billedeskærevej 17", style = value),
            ),
            Row(
                Column(value = "mileage-2", style = value),
                Column(value = LocalDate.parse("2022-03-06"), style = date),
                Column(value = 4, style = value),
                Column(value = "Billedeskærevej 17", style = value),
                Column(value = "Åbrinken 61", style = value),
            ),
        ),
    )

    val mapped = Table(
        columns = listOf(
            ColumnStyle(width = 20),
            ColumnStyle(width = 50),
            ColumnStyle(width = 6),
            ColumnStyle(width = 12),
            ColumnStyle(width = 10),
            ColumnStyle(width = 14),
            ColumnStyle(width = 100),
            ColumnStyle.default,
            ColumnStyle.default,
            ColumnStyle.default,
            ColumnStyle.default,
            ColumnStyle.default,
            ColumnStyle.default,
            ColumnStyle.default,
            ColumnStyle.default,
        ),
        rows = listOf(
            Row(
                Column(value = "expenses.column.merchant"),
                Column(value = "expenses.column.description"),
                Column(value = "expenses.column.count"),
                Column(value = "expenses.column.amount"),
                Column(value = "expenses.column.currency"),
                Column(value = "expenses.column.date"),
                Column(value = "expenses.column.receipt"),
                Column(value = "URL"),
                Column(value = "blank"),
                Column(value = "double"),
                Column(value = "boolean"),
                Column(value = "instant"),
                Column(
                    value = "localDate",
                    style = CellStyle(
                        horizontalAlignment = HorizontalAlignment.LEFT,
                        font = CellFont(
                            underline = FontUnderline.DOUBLE
                        ),
                        dataFormat = "dd/mm/yyyy"
                    )
                ),
                Column(
                    value = "localTime",
                    style = CellStyle(
                        horizontalAlignment = HorizontalAlignment.RIGHT,
                        font = CellFont(
                            weight = FontWeight.REGULAR,
                            underline = FontUnderline.NONE
                        ),
                        dataFormat = "HH:MM"
                    )
                ),
                Column(
                    value = "styled",
                    style = CellStyle(
                        horizontalAlignment = HorizontalAlignment.GENERAL,
                        font = CellFont(
                            size = 13,
                            indexedColor = IndexedColor.RED,
                            weight = FontWeight.BOLD,
                            underline = FontUnderline.SINGLE
                        ),
                        dataFormat = "#"
                    )
                ),
            ),
            Row(
                Column(value = "IKEA"),
                Column(value = "Office Furniture"),
                Column(value = 6),
                Column(value = BigDecimal("1425.00")),
                Column(value = "DKK"),
                Column(value = LocalDateTimeColumnValue(value = LocalDateTime.parse("2022-06-07T23:21:30"))),
                Column(
                    value = Hyperlink(
                        text = "https://www.ikea.com/dk/da/p/lagkapten-alex-skrivebord-turkisgra-sort-s19523513/",
                        uri = URI("https://www.ikea.com/dk/da/p/lagkapten-alex-skrivebord-turkisgra-sort-s19523513/")
                    )
                ),
                Column(
                    value = Hyperlink(
                        text = "LAGKAPTEN / ALEX Skrivebord, turkisgrå/sort, 140x60 cm - IKEA",
                        uri = URI("https://www.ikea.com/dk/da/p/lagkapten-alex-skrivebord-turkisgra-sort-s19523513/")
                    )
                ),
                Column(),
                Column(value = 5.52),
                Column(value = true),
                Column(value = Instant.parse("2023-03-01T23:04:12Z")),
                Column(
                    value = LocalDate.parse("2022-01-23"),
                    style = CellStyle(
                        horizontalAlignment = HorizontalAlignment.LEFT,
                        font = CellFont(
                            underline = FontUnderline.DOUBLE
                        ),
                        dataFormat = "dd/mm/yyyy"
                    )
                ),
                Column(
                    value = LocalTime.parse("11:33:05"),
                    style = CellStyle(
                        horizontalAlignment = HorizontalAlignment.RIGHT,
                        font = CellFont(
                            weight = FontWeight.REGULAR,
                            underline = FontUnderline.NONE
                        ),
                        dataFormat = "HH:MM"
                    )
                ),
                Column(
                    value = NumberColumnValue(value = 5.52),
                    style = CellStyle(
                        horizontalAlignment = HorizontalAlignment.GENERAL,
                        font = CellFont(
                            size = 13,
                            indexedColor = IndexedColor.RED,
                            weight = FontWeight.BOLD,
                            underline = FontUnderline.SINGLE
                        ),
                        dataFormat = "#"
                    )
                )
            ),
            Row(
                Column(value = "Power"),
                Column(value = "Computers"),
                Column(value = 1),
                Column(value = BigDecimal("23000.00")),
                Column(value = "DKK"),
                Column(value = LocalDateTime.parse("2022-06-02T12:21:30")),
                Column(
                    value = Hyperlink(
                        text = "https://www.power.dk/computere-og-tablets/computere/baerbar-pc/hp-14s-fq2473no-14-baerbar-pc/p-1846428/",
                        uri = URI("https://www.power.dk/computere-og-tablets/computere/baerbar-pc/hp-14s-fq2473no-14-baerbar-pc/p-1846428/")
                    )
                ),
                Column(
                    value = Hyperlink(
                        text = "HP Bærbar",
                        uri = URI("https://www.power.dk/computere-og-tablets/computere/baerbar-pc/hp-14s-fq2473no-14-baerbar-pc/p-1846428/")
                    )
                ),
                Column(),
                Column(value = 5.52),
                Column(value = true),
                Column(value = Instant.parse("2023-03-01T23:04:12Z")),
                Column(
                    value = LocalDate.parse("2022-01-23"),
                    style = CellStyle(
                        horizontalAlignment = HorizontalAlignment.LEFT,
                        font = CellFont(
                            underline = FontUnderline.DOUBLE
                        ),
                        dataFormat = "dd/mm/yyyy"
                    )
                ),
                Column(
                    value = LocalTime.parse("11:33:05"),
                    style = CellStyle(
                        horizontalAlignment = HorizontalAlignment.RIGHT,
                        font = CellFont(
                            weight = FontWeight.REGULAR,
                            underline = FontUnderline.NONE
                        ),
                        dataFormat = "HH:MM"
                    )
                ),
                Column(
                    value = 5.52,
                    style = CellStyle(
                        horizontalAlignment = HorizontalAlignment.GENERAL,
                        font = CellFont(
                            size = 13,
                            indexedColor = IndexedColor.RED,
                            weight = FontWeight.BOLD,
                            underline = FontUnderline.SINGLE
                        ),
                        dataFormat = "#"
                    )
                )
            )
        )
    )
}
