package io.github.nillerr.ooxml.spreadsheet

interface ColumnNameResolver {
    fun getName(column: ColumnInfo): String
}
