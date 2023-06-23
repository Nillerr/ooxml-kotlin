package io.nillerr.ooxml

class LocalizedColumnNameResolver : DefaultColumnNameResolver() {
    override fun getName(column: ColumnInfo): String {
        return when (column.annotation.name) {
            "expenses.column.merchant" -> "Merchant"
            "expenses.column.description" -> "Description"
            "expenses.column.amount" -> "Amount"
            "expenses.column.currency" -> "Currency"
            "expenses.column.date" -> "Date"
            "expenses.column.receipt" -> "Receipt"
            else -> super.getName(column)
        }
    }
}
