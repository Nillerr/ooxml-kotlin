package io.github.nillerr.ooxml.table

import io.github.nillerr.ooxml.spreadsheet.Hyperlink
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * Represents a column in a table.
 *
 * @property value The value of the column.
 * @property style The style applied to the column (when supported by the target format), if any.
 */
data class Column(
    val value: ColumnValue,
    val style: CellStyle? = null,
) {
    constructor(value: String, style: CellStyle? = null) : this(StringColumnValue(value), style)
    constructor(value: Number, style: CellStyle? = null) : this(NumberColumnValue(value), style)
    constructor(value: Boolean, style: CellStyle? = null) : this(BooleanColumnValue(value), style)
    constructor(value: Instant, style: CellStyle? = null) : this(InstantColumnValue(value), style)
    constructor(value: LocalDate, style: CellStyle? = null) : this(LocalDateColumnValue(value), style)
    constructor(value: LocalTime, style: CellStyle? = null) : this(LocalTimeColumnValue(value), style)
    constructor(value: LocalDateTime, style: CellStyle? = null) : this(LocalDateTimeColumnValue(value), style)
    constructor(value: Hyperlink, style: CellStyle? = null) : this(HyperlinkColumnValue(value), style)
}
