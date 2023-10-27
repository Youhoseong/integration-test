extra["springCloudVersion"] = "2022.0.4"

dependencies {
    implementation(project(":domain"))
    implementation(project(":usecase"))
    implementation("com.h2database:h2")
    api("org.springframework.boot:spring-boot-starter-data-jpa:3.1.0")
    api("org.springframework.kafka:spring-kafka")
    api("org.springframework.kafka:spring-kafka-test")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    api("it.ozimov:embedded-redis:0.7.2") {
        exclude(group = "org.slf4j", module = "slf4j-simple")
    }
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.3")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.bootJar { enabled = false }
tasks.jar { enabled = true }
