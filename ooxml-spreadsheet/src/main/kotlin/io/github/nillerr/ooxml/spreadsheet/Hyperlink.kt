package io.github.nillerr.ooxml.spreadsheet

import java.net.URI

data class Hyperlink(
    val text: String,
    val uri: URI,
)
