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
}

tasks.test {
    useJUnitPlatform()
}