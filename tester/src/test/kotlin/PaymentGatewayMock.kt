import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.WireMockSpring
import org.springframework.web.reactive.function.client.WebClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@StubLoadConfig
class PaymentGatewayMock : DescribeSpec() {
    private lateinit var wireMock: WireMockServer
    private lateinit var webClient: WebClient

    init {
        beforeSpec {
            wireMock = WireMockServer(WireMockSpring.options().dynamicPort())
            wireMock.start()
                .also {
                    wireMock.stubFor(get(urlPathEqualTo("/api/v1/approve"))
                        .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody("{\"message\": \"Hello, World!\"}")));
                }
            webClient = WebClient.builder()
                .baseUrl("http://localhost:${wireMock.port()}")
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