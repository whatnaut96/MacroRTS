plugins {
    java
    kotlin("jvm") version "2.3.20"
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
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.7")
    implementation("jakarta.json:jakarta.json-api:2.1.3")
    runtimeOnly("org.eclipse.parsson:parsson:1.1.5")
    implementation(fileTree("lib") { include("*.jar") })
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("--enable-preview")
    filter {
        isFailOnNoMatchingTests = false
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(listOf("--enable-preview", "--release", "25"))
    options.compilerArgs.add("-Xlint:all")
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
            exclude("ai/synthesis/**")
            exclude("tests/bayesianmodels/**")
        }
        resources { setSrcDirs(listOf("resources")) }
    }
    test {
        java { setSrcDirs(listOf("test")) }
    }
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveFileName.set("macrorts.jar")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    mergeServiceFiles()
    exclude("bots/**")
    manifest {
        attributes["Main-Class"] = "rts.MacroRTS"
    }
}
