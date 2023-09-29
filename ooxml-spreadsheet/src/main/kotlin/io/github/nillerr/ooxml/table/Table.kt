package io.github.nillerr.ooxml.table

/**
 * Represents a table that can be rendered in a spreadsheet or document.
 *
 * @param name The name of the sheet this table is rendered in (if supported by the target format).
 * @param columns The styles of the columns in the sheet this table is rendered in (if supported by the target format).
 * @param rows The rows of the table.
 */
data class Table(
    val name: String? = null,
    val columns: List<ColumnStyle> = emptyList(),
    val rows: List<Row>,
)
