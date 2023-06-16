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
}

tasks.test {
    useJUnitPlatform()
}