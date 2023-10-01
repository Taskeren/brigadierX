import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.0"
    `maven-publish`
}

group = "com.github.taskeren"
version = "1.3-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
}

dependencies {
    api("com.mojang:brigadier:1.0.18")

    // JUnit5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xcontext-receivers"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.taskeren"
            artifactId = "brigadierX"
            version = "1.3.1"

            from(components["java"])
        }
    }
}