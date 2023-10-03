package io.github.nillerr.ooxml.table.sxsff

import java.time.Clock

class SXSFFTableEncoderOptions {
    var applicationName: String = "io.nillerr.ooxml:spreadsheet"
    var applicationVersion: String = "1.0.2"

    var clock: Clock = Clock.systemDefaultZone()
}
