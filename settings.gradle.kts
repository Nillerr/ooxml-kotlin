rootProject.name = "ooxml"

pluginManagement {
    repositories {
        mavenCentral()
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        kotlin("jvm") version kotlinVersion apply false
        id("org.jetbrains.dokka") version kotlinVersion apply false
    }
}

include(":ooxml-spreadsheet")
