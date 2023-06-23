package io.nillerr.ooxml.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
annotation class Column(
    val name: String = "",
    val width: Int = -1,
    val headerStyle: CellStyle = CellStyle(),
    val style: CellStyle = CellStyle(),
)
