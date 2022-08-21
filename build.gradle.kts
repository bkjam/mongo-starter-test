plugins {
    base
    kotlin("jvm") version "1.7.10" apply false
    kotlin("plugin.spring") version "1.7.10" apply false
}

allprojects {
    group = "com.example"
    repositories {
        mavenCentral()
    }
}

tasks.wrapper {
    gradleVersion = "7.4.2"
}
