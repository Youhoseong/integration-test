apply(plugin = "kotlin-noarg")

dependencies {
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-contract-wiremock
    implementation("org.springframework.cloud:spring-cloud-contract-wiremock:4.0.0")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-contract-stub-runner
    implementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner:4.0.3")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.5.5")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.5")
    testImplementation("io.kotest:kotest-property-jvm:5.5.5")
    testImplementation("io.kotest:kotest-framework-datatest:5.5.5")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
}

tasks.bootJar { enabled = false }
tasks.jar { enabled = true }