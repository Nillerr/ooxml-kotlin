package io.github.nillerr.ooxml.spreadsheet

interface SheetNameResolver {
    fun getSheetName(typeInfo: TypeInfo): String
}
