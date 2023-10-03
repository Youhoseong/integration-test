package com.example.payweb

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.reactive.function.client.WebClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentGatewayMock : DescribeSpec() {
    private lateinit var wireMock: WireMockServer
    private lateinit var webClient: WebClient

    init {
        beforeSpec {
            wireMock = WireMockServer(WireMockConfiguration.options().port(8080))
                .also {
                    it.start()
                }
            webClient = WebClient.builder()
                .baseUrl(wireMock.baseUrl())
                .build()
        }

        afterSpec {
            wireMock.shutdown()
        }

        describe("call stub") {
            context("approve") {
                it("after approve get hello world") {
                    val response = webClient.get()
                        .uri("/api/v1/approve")
                        .retrieve()
                        .bodyToMono(Response::class.java)
                        .block()!!

                    response.message shouldBe "Hello, World!"
                }
            }
        }
    }
}

data class Response(val message: String) {
    constructor() : this("")
}