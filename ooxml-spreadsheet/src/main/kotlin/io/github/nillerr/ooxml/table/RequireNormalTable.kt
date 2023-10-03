package io.github.nillerr.ooxml.table

import java.lang.IllegalArgumentException

internal fun requireRegularTable(table: Table) {
    var numberOfColumns = -1

    table.rows.forEachIndexed { rowIndex, row ->
        val rowNumberOfColumns = row.columns.size

        if (numberOfColumns != -1 && rowNumberOfColumns != numberOfColumns) {
            val message = "The number of columns `$rowNumberOfColumns` on row `$rowIndex` differs from the number of " +
                    "columns `${numberOfColumns}` of a previous row."
            throw IllegalArgumentException(message)
        }

        numberOfColumns = rowNumberOfColumns
    }
}
