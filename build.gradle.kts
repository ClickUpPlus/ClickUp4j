plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenCentral()
}

dependencies {
}

group = "pw.chew.clickup"
version = "1.0-SNAPSHOT"
description = "ClickUp4j"
java.sourceCompatibility = JavaVersion.VERSION_17

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications.create<MavenPublication>("maven") {
        groupId = project.group.toString()
        artifactId = "Chewbotcca"
        version = project.version.toString()

        from(components["java"])
    }

    repositories {
        maven {
            url = uri("https://m2.chew.pro/snapshots")
            credentials {
                username = properties["mchew-username"].toString()
                password = properties["mchew-password"].toString()
            }
        }
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
