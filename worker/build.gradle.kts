dependencies {
    implementation(project(":domain"))
    implementation(project(":usecase"))
    implementation(project(":adapter"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

sourceSets {
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