package io.nillerr.poi.ooxml

import org.apache.poi.xssf.streaming.SXSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.time.Clock
import java.util.*

internal class ApacheWorkbookFactory(
    val clock: Clock,
    var applicationName: String,
    var applicationVersion: String,
) {
    fun createWorkbook(): SXSSFWorkbook {
        val xss = XSSFWorkbook()

        val props = xss.properties

        val core = props.coreProperties
        val now = clock.instant()
        core.setModified(Optional.of(Date.from(now)))

        val extendedProperties = props.extendedProperties
        extendedProperties.application = applicationName
        extendedProperties.appVersion = applicationVersion

        return SXSSFWorkbook(xss)
    }
}
