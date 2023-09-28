package io.github.nillerr.ooxml.table

data class Table(
    val rows: List<Row>,
) {
    constructor(vararg rows: Row) : this(rows.toList())
}
