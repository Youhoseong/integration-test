dependencies {
    implementation(project(":domain"))
    implementation(project(":usecase"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.0")
    implementation("com.h2database:h2")
}

tasks.bootJar { enabled = false }
tasks.jar { enabled = true }
