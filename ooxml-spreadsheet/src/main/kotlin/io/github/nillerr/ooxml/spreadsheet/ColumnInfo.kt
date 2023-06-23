package io.github.nillerr.ooxml.spreadsheet

import io.github.nillerr.ooxml.spreadsheet.annotation.Column
import kotlin.reflect.KType

data class ColumnInfo(
    val annotation: Column,
    val propertyName: String,
    val propertyType: KType,
)
