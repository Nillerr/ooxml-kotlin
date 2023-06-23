package io.nillerr.ooxml.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
annotation class Sheet(
    val name: String = "",
    val headerStyle: CellStyle = CellStyle(),
    val valueStyle: CellStyle = CellStyle(),
)
