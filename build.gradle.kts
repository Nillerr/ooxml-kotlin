import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("signing")
    id("maven-publish")

    kotlin("jvm")
}

allprojects {
    group = "io.github.nillerr"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }

    apply(plugin = "java-library")
    apply(plugin = "signing")
    apply(plugin = "maven-publish")
    apply(plugin = "kotlin")
}

subprojects {
    dependencies {
        testImplementation(kotlin("test"))
    }

    tasks {
        test {
            useJUnitPlatform()
        }

        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    val sourcesJar by tasks.registering(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    publishing {
        repositories {
            maven {
                name = "Sonatype"

                val repository = findProperty("sonatype.repository")
                if (repository == null) {
                    url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
                } else {
                    url = uri("https://s01.oss.sonatype.org/service/local/staging/deployByRepositoryId/$repository")
                }

                credentials {
                    username = property("sonatype.username") as String
                    password = property("sonatype.password") as String
                }
            }

            maven {
                name = "SonatypeSnapshot"
                url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

                credentials {
                    username = property("sonatype.username") as String
                    password = property("sonatype.password") as String
                }
            }
        }

        publications {
            create<MavenPublication>("maven") {
                from(components["java"])

                artifact(sourcesJar.get())

                pom {
                    name.set("OOXML for Kotlin")
                    description.set("Provides means of generating OOXML documents using Kotlin data classes.")
                    url.set("https://github.com/Nillerr/ooxml-kotlin")

                    licenses {
                        license {
                            name.set("MIT")
                            url.set("https://github.com/Nillerr/ooxml-kotlin/LICENSE")
                        }
                    }

                    developers {
                        developer {
                            id.set("Nillerr")
                            name.set("Nicklas Jensen")
                            url.set("https://github.com/Nillerr")
                        }
                    }

                    scm {
                        url.set("https://github.com/Nillerr/ooxml-kotlin")
                    }
                }
            }
        }
    }

    signing {
        val keyId = property("io.github.nillerr.signing.key_id") as String
        val secretKey = property("io.github.nillerr.signing.secret_key") as String
        val password = property("io.github.nillerr.signing.password") as String

        useInMemoryPgpKeys(keyId, secretKey, password)

        sign(publishing.publications)
    }
}
