package com.example.payweb.config

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.common.SingleRootFileSource
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.SmartLifecycle
import org.springframework.context.annotation.Bean

// Only loaded on TestApplicationContext. Do not affect on real ApplicationContext
@TestConfiguration
class EmbeddedMockServer : SmartLifecycle {
    private val logger = LoggerFactory.getLogger(EmbeddedMockServer::class.java)

    private val configuration = WireMockConfiguration().port(5050)
        .fileSource(SingleRootFileSource("src/main/resources"))
        .notifier(ConsoleNotifier(true))

    @Bean
    fun wireMockServer(): WireMockServer {
        return WireMockServer(configuration)
    }

    override fun start() {
        wireMockServer().start()
        logger.info("EmbeddedMockServer is Running on port(s) :${wireMockServer().port()}")
    }

    override fun stop() {
        logger.info("EmbeddedMockServer Shutdown initiated...")
        wireMockServer().shutdown()
        logger.info("EmbeddedMockServer Shutdown Completed.")
    }

    override fun isRunning(): Boolean {
        return wireMockServer().isRunning
    }
}