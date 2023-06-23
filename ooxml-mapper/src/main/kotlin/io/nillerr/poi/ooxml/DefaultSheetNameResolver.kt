package io.nillerr.poi.ooxml

class DefaultSheetNameResolver : SheetNameResolver {
    override fun getSheetName(typeInfo: TypeInfo): String {
        if (typeInfo.sheet.name.isNotEmpty()) {
            return typeInfo.sheet.name
        }

        return typeInfo.type.simpleName!!
    }
}
