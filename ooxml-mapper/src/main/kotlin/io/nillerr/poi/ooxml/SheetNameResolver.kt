package io.nillerr.poi.ooxml

interface SheetNameResolver {
    fun getSheetName(typeInfo: TypeInfo): String
}
