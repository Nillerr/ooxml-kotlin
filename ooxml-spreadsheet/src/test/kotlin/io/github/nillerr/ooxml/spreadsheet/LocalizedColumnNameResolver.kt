package io.github.nillerr.ooxml.spreadsheet

import io.github.nillerr.ooxml.databind.DefaultColumnNameResolver
import io.github.nillerr.ooxml.databind.annotations.Column
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1

class LocalizedColumnNameResolver : DefaultColumnNameResolver() {
    override fun getColumnName(annotation: Column, parameter: KParameter, property: KProperty1<*, Any?>): String {
        return when (annotation.name) {
            "expenses.column.merchant" -> "Merchant"
            "expenses.column.description" -> "Description"
            "expenses.column.amount" -> "Amount"
            "expenses.column.currency" -> "Currency"
            "expenses.column.date" -> "Date"
            "expenses.column.receipt" -> "Receipt"
            else -> super.getColumnName(annotation, parameter, property)
        }
    }
}
