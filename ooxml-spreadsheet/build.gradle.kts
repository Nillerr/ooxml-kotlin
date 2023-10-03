dependencies {
    // Kotlin
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    // Apache POI
    implementation("org.apache.poi:poi-ooxml:5.2.3")

    // Jackson
    val jackson_version = "2.15.2"
    testImplementation("com.fasterxml.jackson.core:jackson-databind:$jackson_version")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:$jackson_version")
    testImplementation("com.fasterxml.jackson.module:jackson-module-parameter-names:$jackson_version")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson_version")
}
