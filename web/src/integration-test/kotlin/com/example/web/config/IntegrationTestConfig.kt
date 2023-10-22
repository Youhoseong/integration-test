package com.example.web.config

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class IntegrationTestConfig
