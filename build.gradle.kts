
plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application
    // You can run your app via task "run": ./gradlew run
    application

    /*
     * Adds tasks to export a runnable jar.
     * In order to create it, launch the "shadowJar" task.
     * The runnable jar will be found in build/libs/projectname-all.jar
     */
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.danilopianini.gradle-java-qa") version "1.96.0"

    id("org.danilopianini.unibo-oop-gradle-plugin") version "1.1.30"
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }


repositories { // Where to search for dependencies
    mavenCentral()
}

dependencies {
    // Suppressions for SpotBugs
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.9.3")

    // Maven dependencies are composed by a group name, a name and a version, separated by colons
    implementation("com.omertron:API-OMDB:1.5")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.18.2")
    implementation("org.jooq:jool:0.9.15")

    /*
     * Simple Logging Facade for Java (SLF4J) with Apache Log4j
     * See: http://www.slf4j.org/
     */
    val slf4jVersion = "2.0.17"
    implementation("org.slf4j:slf4j-api:$slf4jVersion")

    // Apache commons lang3
    val commonsLangVersion = "3.17.0"
    implementation ("org.apache.commons:commons-lang3:$commonsLangVersion")

    //Google guava
    val guavaVersion = "33.4.8"
    implementation("com.google.guava:guava:$guavaVersion-jre")

    // Logback backend for SLF4J
    runtimeOnly("ch.qos.logback:logback-classic:1.5.18")

    // JUnit API and testing engine
    val jUnitVersion = "5.11.4"
    // when dependencies share the same version, grouping in a val helps to keep them in sync
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")


}

application {
    // Define the main class for the application.
    mainClass.set("it.unibo.monopoly.LaunchApp")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        showStandardStreams = true
    }
}

tasks.javadoc {
    options.encoding = "UTF-8"
    isFailOnError = false
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
