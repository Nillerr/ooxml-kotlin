package io.nillerr.ooxml

interface SheetNameResolver {
    fun getSheetName(typeInfo: TypeInfo): String
}
