package io.nillerr.poi.ooxml

import io.nillerr.poi.ooxml.annotation.Column
import kotlin.reflect.KType

data class ColumnInfo(
    val annotation: Column,
    val propertyName: String,
    val propertyType: KType,
)
