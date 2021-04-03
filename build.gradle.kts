plugins {
    java
    idea
    maven
    kotlin("jvm") version "1.4.31"
}

group = "cn.taskeren.brigadierx"
version = "1.1-SNAPSHOT"

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
    maven("http://maven.aliyun.com/nexus/content/repositories/jcenter")
    maven("https://jitpack.io")
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.github.Mojang:brigadier:1.0.17")

    // JUnit5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

tasks.test {
    useJUnitPlatform()
}