dependencies {
    implementation(project(":domain"))
    implementation(project(":usecase"))
    implementation("com.h2database:h2")
    api("org.springframework.boot:spring-boot-starter-data-jpa:3.1.0")
    api("org.springframework.kafka:spring-kafka")
    api("org.springframework.kafka:spring-kafka-test")
}

tasks.bootJar { enabled = false }
tasks.jar { enabled = true }
