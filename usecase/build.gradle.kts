dependencies {
    implementation(project(":domain"))
}

tasks.bootJar { enabled = false }
tasks.jar { enabled = true }
