plugins {
    java
    application
    id("com.gradleup.shadow") version "9.4.0"
}

group = "com.khill.macrorts"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

dependencies {
    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // CLI
    implementation("info.picocli:picocli:4.7.7")

    // JSON (API + implementation)
    implementation("jakarta.json:jakarta.json-api:2.1.3")
    runtimeOnly("org.eclipse.parsson:parsson:1.1.5")

    // Local jars
    implementation(fileTree("lib") {
        include("*.jar")
        exclude("weka.jar")
    })
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.add("--enable-preview")
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs("--enable-preview")
}

application {
    mainClass = "rts.MacroRTS"
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src"))
        }
        resources {
            setSrcDirs(listOf("src")) // Only if you have config files/images in there
        }
    }
    // If your tests are also directly in src/tests
    test {
        java {
            setSrcDirs(listOf("src"))
        }
    }
}

// Shadow JAR
tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveFileName.set("macrorts.jar")

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    mergeServiceFiles()

    exclude("bots/**")

    manifest {
        attributes["Main-Class"] = "rts.MacroRTS"
    }
}