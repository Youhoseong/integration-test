dependencies {
    implementation(project(":domain"))
    implementation(project(":usecase"))
    implementation(project(":adapter"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-contract-wiremock
    implementation("org.springframework.cloud:spring-cloud-contract-wiremock:4.0.0")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-contract-stub-runner
    implementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner:4.0.3")
    implementation("org.springframework.data:spring-data-jpa:3.1.2")
}

sourceSets {
    main {
        kotlin.srcDirs("src/main/kotlin")
    }
    test {
        kotlin.srcDirs("src/test/kotlin")
    }
    create("integrationTest") {
        kotlin.srcDirs("src/integration-test/kotlin")
        resources.srcDirs("src/integration-test/resources")
        compileClasspath += sourceSets["test"].output
        runtimeClasspath += sourceSets["test"].output
    }
}

configurations["integrationTestImplementation"].extendsFrom(configurations.testImplementation.get())
configurations["integrationTestRuntimeOnly"].extendsFrom(configurations.testRuntimeOnly.get())

tasks.register<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "verification"
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    useJUnitPlatform()
    testLogging {
        events("passed")
    }
}