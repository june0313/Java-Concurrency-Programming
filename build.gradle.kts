plugins {
    id("java")
    id("io.freefair.lombok") version("8.11")
}

group = "io.concurrency"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.4.1")
    implementation("org.slf4j:slf4j-api:2.0.3")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}