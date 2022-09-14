plugins {
    val kotlinVersion = "1.6.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.11.1"
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "17"
    }
}
tasks.withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
    dependsOn("copyWebResources")
}
tasks.register<Copy>("copyWebResources") {
    from("MaidNanaFrontEnd/dist")
    into("build/resources/main/META-INF/public")
}

group = "com.github.nanoyou"
version = "0.2.0-alpha"

var ktorVersion = "2.1.0"

repositories {
    maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    mavenCentral()
}
dependencies {
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("it.sauronsoftware.cron4j:cron4j:2.2.5")

    // ktor
    implementation("io.ktor", "ktor-server-netty", ktorVersion)
    implementation("io.ktor", "ktor-server-content-negotiation", ktorVersion)
    implementation("io.ktor", "ktor-serialization-gson", ktorVersion)
    // api 文档
    implementation("io.bkbn:kompendium-core:3.2.0")
}