package io.github.nillerr.ooxml.table

/**
 * Represents the style settings for a column in a table.
 *
 * @property hidden Indicates whether the column is hidden. Default value is false.
 * @property width The width of the column. A negative value (-1) indicates that the width is not set. Default value is -1.
 */
data class ColumnStyle(
    val hidden: Boolean = false,
    val width: Int = -1,
) {
    companion object {
        val default = ColumnStyle()
    }
}
