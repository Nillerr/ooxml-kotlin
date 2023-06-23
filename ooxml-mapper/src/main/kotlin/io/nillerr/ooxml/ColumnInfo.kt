package io.nillerr.ooxml

import io.nillerr.ooxml.annotation.Column
import kotlin.reflect.KType

data class ColumnInfo(
    val annotation: Column,
    val propertyName: String,
    val propertyType: KType,
)
