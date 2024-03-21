import org.gradle.internal.impldep.com.fasterxml.jackson.core.JsonPointer.compile

plugins {
    id("java")
}

group = "org.cyberpsych"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.telegram:telegrambots:6.9.7.1")
    implementation("org.mongodb:mongodb-driver:3.12.14")
    implementation("org.json:json:20240303")
}

tasks.test {
    useJUnitPlatform()
}