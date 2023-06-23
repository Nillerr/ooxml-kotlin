import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("maven-publish")

    kotlin("jvm")
}

group = "io.github.nillerr.ooxml"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }

    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "kotlin")

    publishing {
        publications {
            create<MavenPublication>("maven")
        }
    }

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
}
