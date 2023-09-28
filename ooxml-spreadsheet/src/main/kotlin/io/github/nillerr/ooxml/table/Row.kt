package io.github.nillerr.ooxml.table

data class Row(
    val columns: List<Column>,
) {
    constructor(vararg columns: Column) : this(columns.toList())
}
