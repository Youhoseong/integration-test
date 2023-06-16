plugins {
    id("java")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-contract-wiremock
    implementation("org.springframework.cloud:spring-cloud-contract-wiremock:4.0.0")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-contract-stub-runner
    implementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner:4.0.3")
}

tasks.test {
    useJUnitPlatform()
}