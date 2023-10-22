package com.example.web

import com.example.web.config.IntegrationTestConfig
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.test.web.servlet.MockMvc

@IntegrationTestConfig
class SendMoneyTest(
    private val mockMvc: MockMvc,
) : FunSpec() {
    override fun extensions(): List<Extension> = listOf(SpringExtension)

    init {
        context("송금") {
            test("송금 후 송금 내역이 저장됨") {
                // arrange

                // act

                // assert
                1 shouldBe 1
            }
        }
    }
}