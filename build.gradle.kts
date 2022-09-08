plugins {
    kotlin("jvm") version "1.7.10"
}

group = "cn.taskeren.brigadierx"
version = "1.2-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21")

    api("com.github.Mojang:brigadier:1.0.18")

    // JUnit5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.test {
    useJUnitPlatform()
}