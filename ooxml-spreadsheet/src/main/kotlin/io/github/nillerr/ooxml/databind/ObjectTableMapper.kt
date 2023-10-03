package io.github.nillerr.ooxml.databind

import io.github.nillerr.ooxml.databind.annotations.isEqualTo
import io.github.nillerr.ooxml.table.CellFont
import io.github.nillerr.ooxml.table.CellStyle
import io.github.nillerr.ooxml.table.Column
import io.github.nillerr.ooxml.table.ColumnStyle
import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.table.Row
import io.github.nillerr.ooxml.table.Table
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.jvmName
import io.github.nillerr.ooxml.databind.annotations.CellFont as CellFontAnnotation
import io.github.nillerr.ooxml.databind.annotations.CellStyle as CellStyleAnnotation
import io.github.nillerr.ooxml.databind.annotations.Column as ColumnAnnotation

class ObjectTableMapper(options: ObjectTableMapperOptions) {
    private val cellValueSerializers: List<CellValueSerializer> = options.cellValueSerializers.toList()
    private val columnNameResolver: ColumnNameResolver = options.columnNameResolver
    private val dataFormatResolver: DataFormatResolver = options.dataFormatResolver

    private val defaultColumnAnnotation = ColumnAnnotation()

    constructor() : this(ObjectTableMapperOptions())

    fun toTable(rows: List<Any>): Table {
        return toTable(null, rows)
    }

    fun toTable(name: String?, rows: List<Any>): Table {
        require(rows.isNotEmpty()) { "A table must contain at least one row" }

        val columnStyles = toColumnStyles(rows)
        val header = toHeaderRow(rows)
        val values = rows.map(::toRow)

        return Table(name, columnStyles, listOf(header) + values)
    }

    private fun toColumnStyles(rows: List<Any>): List<ColumnStyle> {
        val styles = LinkedHashMap<String, ColumnStyle>()

        rows.forEach { row ->
            val kClass = row::class
            val properties = kClass.parameterProperties

            properties.forEach { (parameter, property) ->
                val annotation = parameter.findAnnotation<ColumnAnnotation>() ?: defaultColumnAnnotation
                styles[property.name] = annotation.toColumnStyle()
            }
        }

        return styles.values.toList()
    }

    private fun ColumnAnnotation.toColumnStyle(): ColumnStyle {
        return when {
            isColumnStyleEqualTo(defaultColumnAnnotation) -> ColumnStyle.default
            else -> ColumnStyle(hidden = hidden, width = width)
        }
    }

    private fun ColumnAnnotation.isColumnStyleEqualTo(other: ColumnAnnotation): Boolean {
        return hidden == other.hidden && width == other.width
    }

    private fun toHeaderRow(rows: List<Any>): Row {
        val styles = LinkedHashMap<String, CellStyle?>()

        rows.forEach { row ->
            val kClass = row::class
            val properties = kClass.parameterProperties

            properties.forEach { (parameter, property) ->
                val annotation = parameter.findAnnotation<ColumnAnnotation>() ?: defaultColumnAnnotation

                val columnName = columnNameResolver.getColumnName(annotation, parameter, property)
                styles[columnName] = annotation.toHeaderCellStyle(parameter, property)
            }
        }

        val columns = styles.map { (name, style) -> Column(name, style) }
        return Row(columns)
    }

    private fun ColumnAnnotation.toHeaderCellStyle(parameter: KParameter, property: KProperty1<*, Any?>): CellStyle? {
        return when {
            style.isEqualTo(defaultColumnAnnotation.headerStyle) -> null
            else -> style.toCellStyle(parameter, property)
        }
    }

    private fun toRow(row: Any): Row {
        val kClass = row::class
        val properties = kClass.parameterProperties

        val columns = properties.map { (parameter, property) ->
            val gettableProperty = uncheckedCast<KProperty1<Any, Any?>>(property)
            val value = gettableProperty.get(row)
            val columnValue = serialize(value)
            val annotation = parameter.findAnnotation<ColumnAnnotation>() ?: defaultColumnAnnotation
            val style = annotation.toCellStyle(parameter, property)
            return@map Column(columnValue, style)
        }

        return Row(columns)
    }

    private fun ColumnAnnotation.toCellStyle(parameter: KParameter, property: KProperty1<*, Any?>): CellStyle? {
        return when {
            style.isEqualTo(defaultColumnAnnotation.style) -> null
            else -> style.toCellStyle(parameter, property)
        }
    }

    private fun CellStyleAnnotation.toCellStyle(parameter: KParameter, property: KProperty1<*, Any?>): CellStyle {
        val actualDataFormat = dataFormatResolver.getDataFormat(dataFormat, parameter, property)
        val cellFont = font.toCellFont()
        return CellStyle(horizontalAlignment = horizontalAlignment, font = cellFont, dataFormat = actualDataFormat)
    }

    private fun CellFontAnnotation.toCellFont(): CellFont {
        return CellFont(size = size, indexedColor = indexedColor, weight = weight, underline = underline)
    }

    private fun serialize(value: Any?): ColumnValue {
        val columnValue = cellValueSerializers.firstNotNullOfOrNull { it.serialize(value) }

        requireNotNull(columnValue) {
            val valueDescription = value?.let { "of type `${it::class.jvmName}`" } ?: "`null`"
            "A ColumnValueSerializer for the value $valueDescription could not be found"
        }

        return columnValue
    }
}
