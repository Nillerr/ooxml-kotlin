package io.github.nillerr.ooxml.table.csv

import java.nio.charset.Charset

class CSVTableEncoderOptions {
    /**
     * Specifies the separator character. The default value is `,`.
     */
    var separator: String = ","

    /**
     * Encodes the separator character as meta-data in the CSV using the `sep=$separator` syntax. This is typically
     * only supported by Microsoft Excel. The default value is `false`.
     */
    var encodeSeparator: Boolean = false

    /**
     * Specifies the charset used when writing the table.
     */
    var charset: Charset = Charsets.UTF_8

    /**
     * Determines whether to include a byte order mark (BOM) in the output. This is required by Microsoft Excel and is
     * well-supported by various other popular tools such as Google Sheets and Apple Numbers. Default value is `true`.
     */
    var includeBOM: Boolean = true
}
