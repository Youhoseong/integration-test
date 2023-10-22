package com.example.web

import com.example.adapter.jpa.SendMoneyHistoryJpaRepository
import com.example.adapter.jpa.WalletJpaEntity
import com.example.adapter.jpa.WalletJpaRepository
import com.example.domain.sendmoney.SendMoney
import com.example.domain.sendmoney.SendMoneyRepository
import com.example.domain.sendmoney.SendMoneyStatus
import com.example.web.config.IntegrationTestConfig
import com.example.web.controller.SendMoneyController
import com.example.web.fixture.SendMoneyRequestFixture
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import io.kotest.assertions.withClue
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@IntegrationTestConfig
class SendMoneyTest(
    private val mockMvc: MockMvc,
    private val walletJpaRepository: WalletJpaRepository,
    private val sendMoneyJpaRepository: SendMoneyRepository,
    private val sendMoneyHistoryJpaRepository: SendMoneyHistoryJpaRepository,
    private val objectMapper: ObjectMapper,
) : FunSpec() {
    override fun extensions(): List<Extension> = listOf(SpringExtension)
    private val gson = Gson()

    init {
        context("유저 간 송금") {
            test("송금 후, 관련 데이터가 정상적으로 저장됨") {
                // arrange
                val fromUserId = 1L
                val toUserId = 2L
                initializeWallet(fromUserId, amount = 1000)
                initializeWallet(toUserId, amount = 0)
                val request = SendMoneyRequestFixture.of(fromUserId, toUserId, amount = 1000)

                // act
                val response = sendMoney(request)

                // assert
                withClue("SendMoney(송금) 데이터 검증") {
                    val sendMoney = sendMoneyJpaRepository.findById(response.id)
                    checkNotNull(sendMoney)
                    sendMoney.fromUserId shouldBe fromUserId
                    sendMoney.toUserId shouldBe toUserId
                    sendMoney.amount shouldBe request.amount
                    sendMoney.status shouldBe SendMoneyStatus.SUCCESS
                }
                withClue("Wallet(지갑) 데이터 검증") {
                    val fromUserWallet = walletJpaRepository.findByUserId(fromUserId)
                    checkNotNull(fromUserWallet)
                    fromUserWallet.balanceAmount shouldBe 0 // 1,000원(기존) - 1,000원(송금 한 금액) = 0원
                    val toUserWallet = walletJpaRepository.findByUserId(toUserId)
                    checkNotNull(toUserWallet)
                    toUserWallet.balanceAmount shouldBe 1000 // 0원(기존) + 1,000원(송금 받은 금액) = 1,000원
                }
            }
        }
    }

    private fun initializeWallet(
        userId: Long,
        amount: Long,
    ) {
        val wallet = walletJpaRepository.findByUserId(userId)
        val updatedWallet = when (wallet == null) {
            true -> WalletJpaEntity(userId = userId, balanceAmount = amount)
            false -> {
                wallet.balanceAmount = amount
                wallet
            }
        }
        walletJpaRepository.save(updatedWallet)
    }

    private fun sendMoney(request: SendMoneyController.SendMoneyRequest): SendMoney {
        val requestJson = gson.toJson(request)
        val result = mockMvc.post("/api/v1/send-money") {
            contentType = MediaType.APPLICATION_JSON
            content = requestJson.trimIndent()
        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn()
        return objectMapper.readValue(result.response.contentAsString, SendMoney::class.java)
    }
}


