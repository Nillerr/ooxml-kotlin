package io.github.nillerr.ooxml.table

/**
 * Represents a column in a table.
 *
 * @property value The value of the column.
 * @property style The style applied to the column (when supported by the target format), if any.
 */
data class Column(
    val value: ColumnValue,
    val style: CellStyle? = null,
)
