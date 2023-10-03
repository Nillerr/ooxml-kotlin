# OOXML for Kotlin

Provides means of generating OOXML and CSV documents using a common `Table` API, as well as through Jackson-like 
data-binding of Kotlin `data class`es.

## Installation

```kotlin
dependencies {
    implementation("io.github.nillerr:ooxml-spreadsheet:1.0.3")
}
```

## Spreadsheet

To create a spreadsheet we use a combination of the `ObjectTableMapper` and the `SXSFFTableEncoder`, which we provide 
with a list of instances of a `data class`:

```kotlin
data class Expense(
    val id: String,
    val merchant: String,
    val description: String,
    val date: LocalDate,
    val amount: BigDecimal,
    val currency: String,
)

fun createExpenseReport(expenses: List<Expense>) {
    val file = FileOutputStream("expense-report.xlsx")
    
    val mapper = ObjectTableMapper()
    val table = mapper.toTable(expenses)
    
    val encoder = SXSFFTableEncoder()
    encoder.encode(table, file)
    
    file.close()
}
```

This will generate an XLSX file with a sheet, containing a row of header columns with the name of each property, and a 
row of values for every `Expense` in the list passed to the `ObjectTableMapper`. The columns preserve the order in 
which they were declared in the `data class`.

### Headers

To specify the name of the headers in the sheet you can provide your own implementation of `ColumnNameResolver` using 
the `columnNameResolver` property on `WorkbookEncoder`:

```kotlin
val options = ObjectTableMapperOptions()
options.columnNameResolver = TranslatingColumnNameResolver()

val mapper = ObjectTableMapper(options)
val table = mapper.toTable(expenses)
```

You can also set a static name of the header using the `@Column` annotation:

```kotlin
data class Expense(
    @Column(name = "Expense ID")
    val id: String,
)
```

### Styling

When building a `Table` yourself you the header and cells can be styled through the `style` property of the `Column` 
class.

When using the `ObjectTableMapper`, the header and cells can be styled using the `@Column` annotation:

```kotlin
data class Expense(
    @Column(
        width = 40,
        headerStyle = CellStyle(
            horizontalAlignment = HorizontalAlignment.LEFT,
        ),
        style = CellStyle(
            dataFormat = "dd/mm/yyyy",
            font = CellFont(
                underline = FontUnderline.SINGLE,
            ),
        ),
    )
    val date: LocalDate,
)
```

### Data Formats

The data format of cells can be customized my implementing a `DataFormatResolver` and specifying it using the
`dataFormatResolver` property on `ObjectTableMapper`:

```kotlin
val options = ObjectTableMapperOptions()
options.dataFormatResolver = CustomDataFormatResolver()

val mapper = ObjectTableMapper(options)
val table = mapper.toTable(expenses)
```

You can also set a static data format for the column using the `CellStyle` in the `@Column` annotation:

```kotlin
data class Expense(
    @Column(style = CellStyle(dataFormat = "dd/mm/yyyy"))
    val date: LocalDate,
)
```

### Custom Serialization

Serialization of the following types are supported out of the box:

1. `String`
2. `Boolean`
3. `Number`
4. `Instant`
5. `LocalDate`
6. `LocalTime`
7. `LocalDateTime`
8. `Hyperlink`
9. `URI`
10. `URL`

If you want to add support for another type, you must implement a `CellSerializer`:

```kotlin
class InetAddressSerializer : CellValueSerializer {
    override fun serialize(value: Any?): ColumnValue? {
        return when (value) {
            is InetAddress -> value.toString()
            else -> null
        }
    }
}
```

Then register your serializer with the `ObjectTableMapper`:

```kotlin
val options = ObjectTableMapperOptions()
options.cellValueSerializers.add(InetAdressSerializer())

val mapper = ObjectTableMapper(options)
val table = mapper.toTable(expenses)
```

You can now include properties with the type `InetAddress` in the `data class` you use for your row items.
