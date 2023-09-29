# OOXML for Kotlin

Provides means of generating OOXML documents using Kotlin `data class`es.

## Installation

```kotlin
dependencies {
    implementation("io.github.nillerr:ooxml-spreadsheet:1.0.2")
}
```

## Spreadsheet

To create a spreadsheet we use the `WorkbookEncoder`, which we provide with a list of instances of a `data class`:

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
    
    val encoder = WorkbookEncoder()
    encoder.encode(expenses, file)
    
    file.close()
}
```

This will generate an XLSX file with a sheet matching the name of the type of row, which in our example here will be 
`Expense`, and header columns with the text of each property. The columns preserve the order in which they are declared 
in the `data class`.

### Naming

There are a few options to control the naming of sheets and headers, which are described in the following sections.

#### Sheet

To specify the name of the sheet in the resulting file you can either provide your own implementation of 
`SheetNameResolver` using the `sheetNameResolver` property on `WorkbookEncoder`:

```kotlin
val encoder = WorkbookEncoder()
encoder.sheetNameResolver = TranslatingSheetNameResolver()
```

You can also set a static name for the sheet using the `@Sheet` annotation:

```kotlin
@Sheet(name = "Expenses")
data class Expense(
    ...
)
```

#### Headers

To specify the name of the headers in the sheet you can provide your own implementation of `ColumnNameResolver` using 
the `columnNameResolver` property on `WorkbookEncoder`:

```kotlin
val encoder = WorkbookEncoder()
encoder.columnNameResolver = TranslatingColumnNameResolver()
```

You can also set a static name of the header using the `@Column` annotation:

```kotlin
data class Expense(
    @Column(name = "Expense ID")
    val id: String,
)
```

### Styling

The header and cells can be styled at several levels:

```kotlin
@Sheet(
    headerStyle = CellStyle(
        horizontalAlignment = HorizontalAlignment.CENTER,
        font = CellFont(
            size = 14,
            weight = FontWeight.BOLD,
        ),
    ),
    valueStyle = CellStyle(
        font = CellFont(
            size = 12,
        ),
    ),
)
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

You can also specify default styling using the `defaultHeaderStyle` and `defaultValueStyle` properties on the 
`WorkbookEncoder`:

```kotlin
val encoder = WorkbookEncoder()
encoder.defaultHeaderStyle = CellStyle(...)
encoder.defaultValueStyle = CellStyle(...)
```

The levels of styling take precedence in the following order (lowest to highest):

1. `defaultHeaderStyle` and `defaultValueStyle`
2. Styles defined in `@Sheet`
3. Styles defined in `@Column`

The styling is **merged** in this order, so one does not exclude the other, but a higher precedence styling can 
override the styling of a lower precedence styling.

### Data Formats

The data format of cells can be customized my implementing a `DataFormatResolver` and specifying it using the
`dataFormatResolver` property on `WorkbookEncoder`:

```kotlin
val encoder = WorkbookEncoder()
encoder.dataFormatResolver = CustomDataFormatResolver()
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
3. `Int`
4. `Double`
5. `BigDecimal`
6. `Instant`
7. `LocalDate`
8. `LocalTime`
9. `LocalDateTime`
10. `Hyperlink`
11. `URI`

If you want to add support for another type, you must implement a `CellSerializer`:

```kotlin
class InetAddressSerializer : CellSerializer<InetAddress> {
    override fun serialize(value: InetAddress, writer: CellWriter, serializers: CellSerializerRegistry) {
        writer.writeString(value.toString())
    }
}
```

Then register your serializer in the `CellSerializerRegistry` of the `WorkbookEncoder`:

```kotlin
val encoder = WorkbookEncoder()
encoder.serializers.register(InetAddressSerializer())
```

You can now include properties with the type `InetAddress` in the `data class` you use for your row items.
