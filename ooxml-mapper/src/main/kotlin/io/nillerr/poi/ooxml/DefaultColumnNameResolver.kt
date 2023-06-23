package io.nillerr.poi.ooxml

open class DefaultColumnNameResolver : ColumnNameResolver {
    override fun getName(column: ColumnInfo): String {
        val declaredName = column.annotation.name
        if (declaredName.isBlank()) {
            return column.propertyName
        }

        return declaredName
    }
}
