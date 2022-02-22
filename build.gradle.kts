/*
 * Copyright 2022 Chew and Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains", "annotations", "23.0.0")

    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("org.json:json:20211205")

    // Add Junit as a test dependency
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

group = "pw.chew.clickup4j"
version = "1.0-SNAPSHOT"
description = "ClickUp4j"
java.sourceCompatibility = JavaVersion.VERSION_1_8

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications.create<MavenPublication>("maven") {
        groupId = project.group.toString()
        artifactId = project.rootProject.name
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

tasks.withType<Test>() {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
