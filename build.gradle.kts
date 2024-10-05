val testcontainersVersion by extra("1.20.1")

plugins {
    java
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.springdoc.openapi-gradle-plugin") version "1.8.0"
    id("pmd")
    id("com.diffplug.spotless") version "6.25.0"
}

group = "com.mcddhub"
version = "0.0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        sourceCompatibility = JavaVersion.VERSION_21
    }
}

repositories {
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-quartz")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.apache.commons:commons-lang3:3.17.0")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    implementation("com.auth0:java-jwt:4.4.0")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
    testImplementation("org.testcontainers:junit-jupiter:$testcontainersVersion")
    testImplementation("org.testcontainers:postgresql:$testcontainersVersion")
    testImplementation("org.testcontainers:testcontainers-bom:$testcontainersVersion")
    runtimeOnly("org.postgresql:postgresql")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.springframework.boot:spring-boot-docker-compose")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

pmd {
    isConsoleOutput = true
    toolVersion = "7.5.0"
    rulesMinimumPriority.set(5)
    ruleSetFiles = files("pmd-rules.xml")
}

spotless {
    format("misc") {
        // define the files to apply `misc` to
        target("*.gradle.kts", "*.md", ".gitignore")
        // define the steps to apply to those files
        trimTrailingWhitespace()
        indentWithSpaces() // or spaces. Takes an integer argument if you don't like 4
        endWithNewline()
    }

    java {
        // don't need to set target, it is inferred from java

        // apply a specific flavor of google-java-format
        googleJavaFormat("1.17.0").reflowLongStrings()
        // fix formatting of type annotations
        formatAnnotations()
    }

    kotlinGradle {
        target("*.gradle.kts") // default target for kotlinGradle
        ktlint() // or ktfmt() or prettier()
    }
}

