package io.nillerr.ooxml

interface ColumnNameResolver {
    fun getName(column: ColumnInfo): String
}
