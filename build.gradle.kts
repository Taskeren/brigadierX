plugins {
    kotlin("jvm") version "1.4.31"
}

group = "cn.taskeren.brigadierx"
version = "1.0-SNAPSHOT"

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
}