package io.github.nillerr.ooxml.table

import java.lang.IllegalArgumentException

fun requireRegularTable(table: Table) {
    var numberOfColumns = -1

    val columnWidths = mutableMapOf<Int, Int>()

    table.rows.forEachIndexed { rownum, row ->
        val rowNumberOfColumns = row.columns.size

        if (numberOfColumns != -1 && rowNumberOfColumns != numberOfColumns) {
            val message = "The number of columns `$rowNumberOfColumns` on row `$rownum` differs from the number of " +
                    "columns `${numberOfColumns}` of a previous row."
            throw IllegalArgumentException(message)
        }

        numberOfColumns = rowNumberOfColumns

        row.columns.forEachIndexed { colnum, column ->
            val columnWidth = column.style?.width ?: -1
            if (columnWidth > -1) {
                val width = columnWidths.getOrPut(colnum) { columnWidth }
                if (width != columnWidth) {
                    val message = "The width `$columnWidth` of column `$colnum` on row `$rownum` differs from the " +
                            "width `$width` of the same column of a previous row."
                    throw IllegalArgumentException(message)
                }
            }
        }
    }
}
