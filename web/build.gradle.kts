dependencies {
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-contract-wiremock
    implementation("org.springframework.cloud:spring-cloud-contract-wiremock:4.0.0")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-contract-stub-runner
    implementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner:4.0.3")
    implementation("com.h2database:h2")
    implementation("org.springframework.data:spring-data-jpa:3.1.2")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.5.5")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.5")
    testImplementation("io.kotest:kotest-property-jvm:5.5.5")
    testImplementation("io.kotest:kotest-framework-datatest:5.5.5")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
}

val integrationTestSourceSet: SourceSet = sourceSets.create("integration-test") {
    compileClasspath += sourceSets.main.get().output
    runtimeClasspath += sourceSets.main.get().output
}

configurations["integrationTestImplementation"].extendsFrom(configurations.testImplementation.get())
configurations["integrationTestRuntimeOnly"].extendsFrom(configurations.testRuntimeOnly.get())

task<Test>("integrationTest") {
    description = "Runs integration tests."
    group = LifecycleBasePlugin.VERIFICATION_GROUP

    testClassesDirs = integrationTestSourceSet.output.classesDirs
    classpath = integrationTestSourceSet.runtimeClasspath
}

