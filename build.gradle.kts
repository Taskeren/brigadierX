plugins {
    java
    idea
    maven
    kotlin("jvm") version "1.4.31"
}

group = "cn.taskeren.brigadierx"
version = "1.2-SNAPSHOT"

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
    maven("http://maven.aliyun.com/nexus/content/repositories/jcenter")
    maven("https://jitpack.io")
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://libraries.minecraft.net")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21")

    api("com.github.Mojang:brigadier:1.0.18")

    // JUnit5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}

tasks.test {
    useJUnitPlatform()
}