package io.nillerr.poi.ooxml

interface ColumnNameResolver {
    fun getName(column: ColumnInfo): String
}
