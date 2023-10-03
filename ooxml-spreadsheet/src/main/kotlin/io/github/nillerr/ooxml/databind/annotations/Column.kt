package io.github.nillerr.ooxml.databind.annotations

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
annotation class Column(
    val name: String = "",
    val hidden: Boolean = false,
    val width: Int = -1,
    val headerStyle: CellStyle = CellStyle(),
    val style: CellStyle = CellStyle(),
)
