package io.github.nillerr.ooxml.table

/**
 * Represents a row in a table.
 *
 * @param columns The list of columns in the row.
 */
data class Row(
    val columns: List<Column>,
) {
    /**
     * @param columns the list of columns in the row.
     */
    constructor(vararg columns: Column) : this(columns.toList())
}
