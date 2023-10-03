package io.github.nillerr.ooxml.table.csv

import io.github.nillerr.ooxml.BooleanColumnValue
import io.github.nillerr.ooxml.ColumnValue
import io.github.nillerr.ooxml.EmptyColumnValue
import io.github.nillerr.ooxml.HyperlinkColumnValue
import io.github.nillerr.ooxml.InstantColumnValue
import io.github.nillerr.ooxml.LocalDateColumnValue
import io.github.nillerr.ooxml.LocalDateTimeColumnValue
import io.github.nillerr.ooxml.LocalTimeColumnValue
import io.github.nillerr.ooxml.NumberColumnValue
import io.github.nillerr.ooxml.StringColumnValue
import io.github.nillerr.ooxml.table.Table
import io.github.nillerr.ooxml.table.TableEncoder
import io.github.nillerr.ooxml.table.requireRegularTable
import java.io.OutputStream
import java.nio.charset.Charset

class CSVTableEncoder(options: CSVTableEncoderOptions) : TableEncoder {
    private val separator: String = options.separator
    private val encodeSeparator: Boolean = options.encodeSeparator

    private val charset: Charset = options.charset
    private val includeBOM: Boolean = options.includeBOM

    constructor() : this(CSVTableEncoderOptions())

    override fun encode(table: Table, output: OutputStream) {
        requireRegularTable(table)

        if (includeBOM) {
            output.write(0xEF)
            output.write(0xBB)
            output.write(0xBF)
        }

        val writer = output.bufferedWriter(charset)

        if (encodeSeparator) {
            writer.write("sep=${separator}")
            writer.write("\n")
        }

        table.rows.forEach { row ->
            row.columns.forEachIndexed { colIndex, col ->
                if (colIndex > 0) {
                    writer.write(separator)
                }

                val valueStr = toValueString(col.value)
                val str = quote(valueStr)
                writer.write(str)
            }

            writer.write("\n")
        }

        writer.flush()
    }
    private fun toValueString(value: ColumnValue): String {
        return when (value) {
            EmptyColumnValue -> ""
            is StringColumnValue -> value.value
            is NumberColumnValue -> value.value.toString()
            is BooleanColumnValue -> value.value.toString()
            is InstantColumnValue -> value.value.toString()
            is LocalDateColumnValue -> value.value.toString()
            is LocalTimeColumnValue -> value.value.toString()
            is LocalDateTimeColumnValue -> value.value.toString()
            is HyperlinkColumnValue -> value.value.uri.toASCIIString()
        }
    }

    private fun quote(str: String): String {
        val escaped = str.replace("\"", "\"\"")
        return "\"$escaped\""
    }
}
