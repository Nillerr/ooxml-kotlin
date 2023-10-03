package io.github.nillerr.ooxml.table

import io.github.nillerr.ooxml.BooleanColumnValue
import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.EmptyColumnValue
import io.github.nillerr.ooxml.Hyperlink
import io.github.nillerr.ooxml.HyperlinkColumnValue
import io.github.nillerr.ooxml.InstantColumnValue
import io.github.nillerr.ooxml.LocalDateColumnValue
import io.github.nillerr.ooxml.LocalDateTimeColumnValue
import io.github.nillerr.ooxml.LocalTimeColumnValue
import io.github.nillerr.ooxml.NumberColumnValue
import io.github.nillerr.ooxml.StringColumnValue
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
    constructor(style: CellStyle? = null) : this(EmptyColumnValue, style)
    constructor(value: String, style: CellStyle? = null) : this(StringColumnValue(value), style)
    constructor(value: Number, style: CellStyle? = null) : this(NumberColumnValue(value), style)
    constructor(value: Boolean, style: CellStyle? = null) : this(BooleanColumnValue(value), style)
    constructor(value: Instant, style: CellStyle? = null) : this(InstantColumnValue(value), style)
    constructor(value: LocalDate, style: CellStyle? = null) : this(LocalDateColumnValue(value), style)
    constructor(value: LocalTime, style: CellStyle? = null) : this(LocalTimeColumnValue(value), style)
    constructor(value: LocalDateTime, style: CellStyle? = null) : this(LocalDateTimeColumnValue(value), style)
    constructor(value: Hyperlink, style: CellStyle? = null) : this(HyperlinkColumnValue(value), style)
}
