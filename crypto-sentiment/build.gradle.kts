plugins {
    java
    id("com.commercehub.gradle.plugin.avro") version "0.99.99"
    id("io.freefair.lombok") version "6.3.0"
}

group = "me.zixuan"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenLocal()
    mavenCentral()
    maven(url = "https://packages.confluent.io/maven/")
}

avro {
    fieldVisibility = "PRIVATE"
}

dependencies {
    implementation("org.apache.kafka:kafka-streams:3.1.0")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("org.apache.avro:avro:1.11.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}