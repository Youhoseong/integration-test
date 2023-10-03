package com.example.payweb

import org.springframework.boot.test.context.TestConfiguration
import javax.annotation.PostConstruct

@TestConfiguration
class EmbeddedMockServer {
    @PostConstruct
    fun init() {
        println("EmbeddedMockServer init")
    }
}