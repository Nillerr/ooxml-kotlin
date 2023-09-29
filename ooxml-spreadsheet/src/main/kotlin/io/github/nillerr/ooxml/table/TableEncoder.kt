package io.github.nillerr.ooxml.table

import java.io.OutputStream

interface TableEncoder {
    /**
     * @throws IllegalArgumentException if the [Table] is not a regular table, meaning or one or more rows have a
     * different number of columns than another.
     */
    fun encode(table: Table, output: OutputStream)
}
